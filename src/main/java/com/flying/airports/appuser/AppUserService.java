package com.flying.airports.appuser;

import com.flying.airports.airport.AirportService;
import com.flying.airports.registration.token.ConfirmationToken;
import com.flying.airports.registration.token.ConfirmationTokenService;
import com.flying.airports.ticket.Ticket;
import com.flying.airports.ticket.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";

    private final AirportService airportService;
    private final TicketService ticketService;

    public List<AppUser> getUsers() {
        return appUserRepository.findAll();
    }

    public AppUser getSingleUser(Long userId) {

        Optional<AppUser> userOptional = appUserRepository.findById(userId);
        if(userOptional.isPresent()) {
            return userOptional.get();
        }
        else {
            throw new IllegalStateException("user with id does not exist");
        }
    }

    @Transactional
    public void purchaseTicket(String takeOffAirport, String landingAirport,String appUserEmail) {

        Optional<AppUser> appUser = appUserRepository.findByEmail(appUserEmail);

        if(appUser.isEmpty()) {
            throw new IllegalStateException("user with that mail does not exist");
        }

        Ticket ticket = ticketService.getSingleTicket(landingAirport);
        appUser.get().setTicket(ticket);
        airportService.getSinglePlane(takeOffAirport, ticket).getUsers().add(appUser.get());
    }

    public String signUpUser(AppUser appUser) {

        Boolean userExists = appUserRepository
                .findByEmail(appUser.getEmail())
                .isPresent();

        if(userExists) {
            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = passwordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        appUserRepository.save(appUser);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;
    }

    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG,email)));
    }
}

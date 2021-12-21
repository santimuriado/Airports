package com.flying.airports.appuser;

import com.flying.airports.registration.token.ConfirmationToken;
import com.flying.airports.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

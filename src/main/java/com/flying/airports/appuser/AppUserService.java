package com.flying.airports.appuser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;

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

}

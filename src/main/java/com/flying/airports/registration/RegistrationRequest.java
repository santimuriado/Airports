package com.flying.airports.registration;

import com.flying.airports.security.ApplicationUserRole;
import lombok.Data;

@Data
public class RegistrationRequest {

    private final String username;
    private final String password;
    private final String email;
    private final ApplicationUserRole role;

}

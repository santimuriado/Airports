package com.flying.airports.appuser;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/appusers")
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;

    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public List<AppUser> getUsers() {
        return appUserService.getUsers();
    }

    @GetMapping(path = "{appUserId}")
    @PreAuthorize("hasAuthority('admin:read')")
    public AppUser getSingleUser(@PathVariable("appUserId") Long appUserId) {
        return appUserService.getSingleUser(appUserId);
    }
}

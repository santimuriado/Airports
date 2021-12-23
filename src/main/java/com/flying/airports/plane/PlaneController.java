package com.flying.airports.plane;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/plane")
@RequiredArgsConstructor
public class PlaneController {

    private final PlaneService planeService;

    @GetMapping
    @PreAuthorize("hasAuthority('user:read')")
    public List<Plane> getPlanes() {
        return planeService.getPlanes();
    }

    @GetMapping(path = "{planeId}")
    @PreAuthorize("hasAuthority('admin:read')")
    public Plane getSinglePlane(@PathVariable("planeId") Long planeId) {
        return planeService.getSinglePlane(planeId);
    }
}

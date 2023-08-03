package com.shinhe.luvpet.controller;

import com.shinhe.luvpet.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class LocationController {
    private final LocationService locationService;
}

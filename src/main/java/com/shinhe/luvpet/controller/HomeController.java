package com.shinhe.luvpet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    @RequestMapping("/")
    public String home(){
        return "home";
    }

    @RequestMapping("/local")
    public String goLocalPage(){
        return "local";
    }
}

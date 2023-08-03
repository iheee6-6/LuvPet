package com.shinhe.luvpet.controller;

import com.shinhe.luvpet.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PetBoardController {
    private final PetService petService;

}

package com.bricomaniaco.ticketappserver.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String redirectToAdminIndex() {
        return "redirect:/admin/index.html";
    }
}
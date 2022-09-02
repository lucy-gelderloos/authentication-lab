package com.gelderloos.authentication.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getHome() {
        return "index";
    }

    @GetMapping("/indexWithSecret")
    public String getHomepageWithSecret(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        String username = session.getAttribute("username").toString();

        model.addAttribute("username", username);

        return "/indexWithSecret";
    }
}

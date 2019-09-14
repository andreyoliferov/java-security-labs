package edu.otib.lab_csrf.controller;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TransferController {
    @GetMapping("/transfer")
    public String transferForm() {
        return "transfer_form";
    }

    @PostMapping(value = "/transfer" /*, consumes = MediaType.APPLICATION_JSON_VALUE*/)
    public String transfer(@RequestParam String to, @RequestParam Integer amount, Model model, Authentication authentication) {
        model.addAttribute("from", authentication.getName());
        model.addAttribute("to", to);
        model.addAttribute("amount", amount);
        return "transfer_result";
    }
}

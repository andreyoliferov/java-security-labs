package edu.otib.lab_idor.controller;

import edu.otib.lab_idor.entity.Payment;
import edu.otib.lab_idor.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class PaymentController {
    @Autowired
    IPaymentService paymentService;

    @GetMapping(value = {"/", "/payments"})
    @PostAuthorize("#model[payments].get(0).getUser() == authentication.name")
    public String getUserPayments(Model model, Authentication authentication) {
        List<Payment> userPayments = paymentService.getPaymentsByUser(authentication.getName());
        model.addAttribute("payments", userPayments);
        return "payments_list";
    }

    @GetMapping("/payments/{id}")
    @PostAuthorize("#model[payment].user == authentication.name") /* защита от IDOR, аутентификация для объекта в model */
    /*@PostAuthorize("returnObject.user == authentication.name")*/
    public String getPaymentById(@PathVariable("id") Integer id, Model model) {
        Payment payment = paymentService.getPaymentById(id);
        model.addAttribute("payment", payment);
        return "payment_details";
    }
}

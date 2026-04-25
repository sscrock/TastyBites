package com.sourabh.fooddelivery.authservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/test")
    public String test() {
        return "Customer API working ✅";
    }
}
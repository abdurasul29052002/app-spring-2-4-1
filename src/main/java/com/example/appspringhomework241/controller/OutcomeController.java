package com.example.appspringhomework241.controller;

import com.example.appspringhomework241.payload.PaymentDto;
import com.example.appspringhomework241.service.OutcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/outcome")
public class OutcomeController {
    @Autowired
    OutcomeService outcomeService;

    @PostMapping
    public HttpEntity<?> addOutcome(@RequestBody PaymentDto paymentDto) {
        return outcomeService.addOutcome(paymentDto);
    }
}

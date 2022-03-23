package com.example.appspringhomework241.controller;

import com.example.appspringhomework241.payload.PaymentDto;
import com.example.appspringhomework241.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/income")
public class IncomeController {
    @Autowired
    IncomeService incomeService;

    @PostMapping
    public HttpEntity<?> addIncome(@RequestBody PaymentDto paymentDto){
        return incomeService.addIncome(paymentDto);
    }
}

package com.example.appspringhomework241.service;

import com.example.appspringhomework241.entity.Card;
import com.example.appspringhomework241.entity.Income;
import com.example.appspringhomework241.entity.Outcome;
import com.example.appspringhomework241.payload.PaymentDto;
import com.example.appspringhomework241.payload.Result;
import com.example.appspringhomework241.repository.CardRepository;
import com.example.appspringhomework241.repository.IncomeRepository;
import com.example.appspringhomework241.repository.OutcomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class IncomeService {
    @Autowired
    IncomeRepository incomeRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    OutcomeService outcomeService;

    @Autowired
    OutcomeRepository outcomeRepository;

    public HttpEntity<?> addIncome(PaymentDto paymentDto) {
        return outcomeService.addOutcome(paymentDto);
    }
}

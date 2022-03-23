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
public class OutcomeService {
    @Autowired
    OutcomeRepository outcomeRepository;

    @Autowired
    IncomeRepository incomeRepository;

    @Autowired
    CardRepository cardRepository;


    public Double commisionService(double amount,double percent){
        return (amount/100)*percent;
    }

    public HttpEntity<?> addOutcome(PaymentDto paymentDto) {
        Optional<Card> optionalFromCard = cardRepository.findById(paymentDto.getFromCardId());
        Optional<Card> optionalToCard = cardRepository.findById(paymentDto.getToCardId());
        if (!optionalFromCard.isPresent())
            return ResponseEntity.status(409).body(new Result("Error",false));
        if (!optionalToCard.isPresent())
            return ResponseEntity.status(409).body(new Result("Error",false));
        Card fromCard = optionalFromCard.get();
        Card toCard = optionalToCard.get();
        Double commisionAmount = commisionService(paymentDto.getAmount(), 1);
        fromCard.setBalance(fromCard.getBalance()-(paymentDto.getAmount()+commisionAmount));
        toCard.setBalance(toCard.getBalance()+paymentDto.getAmount());
        cardRepository.save(toCard);
        cardRepository.save(fromCard);
        Outcome outcome = new Outcome(null,fromCard,toCard,paymentDto.getAmount(),new Timestamp(System.currentTimeMillis()),commisionAmount);
        outcomeRepository.save(outcome);
        Income income = new Income(null,fromCard,toCard,paymentDto.getAmount(),new Timestamp(System.currentTimeMillis()));
        incomeRepository.save(income);
        return ResponseEntity.ok(new Result("Successfully",true));
    }
}

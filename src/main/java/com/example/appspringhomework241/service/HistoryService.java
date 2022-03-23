package com.example.appspringhomework241.service;

import com.example.appspringhomework241.entity.Income;
import com.example.appspringhomework241.entity.Outcome;
import com.example.appspringhomework241.payload.TransferDto;
import com.example.appspringhomework241.repository.CardRepository;
import com.example.appspringhomework241.repository.IncomeRepository;
import com.example.appspringhomework241.repository.OutcomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sun.print.PageableDoc;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryService {
    @Autowired
    IncomeRepository incomeRepository;
    @Autowired
    OutcomeRepository outcomeRepository;

    @Autowired
    CardRepository cardRepository;

    public HttpEntity<?> getCardHistory(Integer cardId) {
        List<Income> incomeList = incomeRepository.findAllByToCardId(cardId);
        List<Outcome> outcomeList = outcomeRepository.findAllByFromCardId(cardId);
        List<TransferDto> history = new ArrayList<>();
        for (Income income : incomeList) {
            history.add(new TransferDto(income.getFromCard(),income.getToCard(),income.getDate(),income.getAmount()));
        }
        for (Outcome outcome : outcomeList) {
            history.add(new TransferDto(outcome.getFromCard(),outcome.getToCard(),outcome.getDate(),outcome.getAmount()+outcome.getCommisionAmount()));
        }
        return ResponseEntity.ok(history);
    }


}

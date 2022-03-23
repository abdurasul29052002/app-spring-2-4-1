package com.example.appspringhomework241.controller;

import com.example.appspringhomework241.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/history")
public class HistoryController {

    @Autowired
    HistoryService historyService;

    @GetMapping("/{cardId}")
    public HttpEntity<?> getCardHistory(@PathVariable Integer cardId){
        return historyService.getCardHistory(cardId);
    }
}

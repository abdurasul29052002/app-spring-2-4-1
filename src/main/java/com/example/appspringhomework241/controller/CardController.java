package com.example.appspringhomework241.controller;

import com.example.appspringhomework241.payload.CardDto;
import com.example.appspringhomework241.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/card")
public class CardController {
    @Autowired
    CardService cardService;

    @PostMapping
    public HttpEntity<?> addCard(@RequestBody CardDto cardDto){
        return cardService.addCard(cardDto);
    }

    @GetMapping
    public HttpEntity<?> getAllCards(){
        return cardService.getAllCards();
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getCard(@PathVariable Integer id) {
        return cardService.getCard(id);
    }

    @GetMapping("/byUser/{userId}")
    public HttpEntity<?> getCardByUserId(@PathVariable Integer userId){
        return cardService.getCardByUserId(userId);
    }

    @GetMapping("/byNumber/{cardNumber}")
    public  HttpEntity<?> getCardByNumber(@PathVariable String cardNumber){
        return cardService.getCardByNumber(cardNumber);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editCard(@PathVariable Integer id,@RequestBody CardDto cardDto){
        return cardService.editCard(id, cardDto);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteCard(@PathVariable Integer id){
        return cardService.deleteCard(id);
    }
}

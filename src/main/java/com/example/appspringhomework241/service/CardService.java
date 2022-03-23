package com.example.appspringhomework241.service;

import com.example.appspringhomework241.entity.Card;
import com.example.appspringhomework241.entity.MyUser;
import com.example.appspringhomework241.payload.CardDto;
import com.example.appspringhomework241.payload.Result;
import com.example.appspringhomework241.repository.CardRepository;
import com.example.appspringhomework241.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    MyUserRepository myUserRepository;

    public HttpEntity<?> addCard(CardDto cardDto) {
        String expireDate = cardDto.getExpireDate();//12/26
        Optional<MyUser> optionalMyUser = myUserRepository.findById(cardDto.getUserId());
        if (!optionalMyUser.isPresent())
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new Result("User not found", false));
        MyUser myUser = optionalMyUser.get();
        Card card = new Card(null, cardDto.getNumber(), cardDto.getExpireDate(), cardDto.getBalance(), myUser, cardDto.isActive());
        cardRepository.save(card);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Result("Card saved", true));
    }

    public HttpEntity<?> getAllCards() {
        return ResponseEntity.ok(cardRepository.findAll());
    }

    public HttpEntity<?> getCard(Integer id) {
        Optional<Card> optionalCard = cardRepository.findById(id);
        return ResponseEntity.status(optionalCard.isPresent() ? 200 : 409).body(optionalCard.orElse(null));
    }

    public HttpEntity<?> editCard(Integer id, CardDto cardDto) {
        Optional<Card> optionalCard = cardRepository.findById(id);
        if (!optionalCard.isPresent())
            return ResponseEntity.status(404).body(new Result("Card not found", false));
        Optional<MyUser> optionalMyUser = myUserRepository.findById(cardDto.getUserId());
        if (!optionalMyUser.isPresent())
            return ResponseEntity.status(409).body(new Result("User not found", false));
        Card card = optionalCard.get();
        card.setNumber(cardDto.getNumber());
        card.setUser(optionalMyUser.get());
        card.setExpireDate(cardDto.getExpireDate());
        card.setBalance(cardDto.getBalance());
        card.setActive(cardDto.isActive());
        cardRepository.save(card);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Result("Card updated", true));
    }

    public HttpEntity<?> deleteCard(Integer id) {
        Optional<Card> optionalCard = cardRepository.findById(id);
        if (!optionalCard.isPresent())
            return ResponseEntity.status(409).body(new Result("Card not found", false));
        Card card = optionalCard.get();
        card.setActive(false);
        cardRepository.save(card);
        return ResponseEntity.ok(new Result("Card deleted",true));
    }

    public HttpEntity<?> getCardByUserId(Integer userId) {
        return ResponseEntity.ok(cardRepository.findAllByUserId(userId));
    }

    public HttpEntity<?> getCardByNumber(String cardNumber) {
        Optional<Card> optionalCard = cardRepository.findByNumber(cardNumber);
        return ResponseEntity.status(optionalCard.isPresent()?200:409).body(optionalCard.orElse(null));
    }
}

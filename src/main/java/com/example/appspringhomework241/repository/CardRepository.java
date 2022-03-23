package com.example.appspringhomework241.repository;

import com.example.appspringhomework241.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card,Integer> {
    List<Card> findAllByUserId(Integer user_id);
    Optional<Card> findByNumber(String number);
}

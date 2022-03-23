package com.example.appspringhomework241.repository;

import com.example.appspringhomework241.entity.Outcome;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OutcomeRepository extends JpaRepository<Outcome,Integer> {
    List<Outcome> findAllByFromCardId(Integer fromCard_id);
}

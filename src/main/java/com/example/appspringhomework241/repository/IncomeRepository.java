package com.example.appspringhomework241.repository;

import com.example.appspringhomework241.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncomeRepository extends JpaRepository<Income,Integer> {
    List<Income> findAllByToCardId(Integer toCard_id);
}

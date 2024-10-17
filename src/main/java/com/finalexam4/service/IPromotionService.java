package com.finalexam4.service;

import com.finalexam4.model.Promotion;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IPromotionService {
    Iterable<Promotion> findAll();

    Optional<Promotion> findById(Long id);

    Promotion save(Promotion promotion);

    void remove(Long id);
    List<Promotion> findByCodeContainingAndDiscount(String code, Double discount,LocalDate startDate, LocalDate endDate);
}

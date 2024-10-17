package com.finalexam4.repository;

import com.finalexam4.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IPromotionRepository extends JpaRepository<Promotion,Long> {
    List<Promotion> findByCodeContainingAndDiscount(String code, Double discount);
    List<Promotion> findByCodeContaining(String code);
    List<Promotion> findByDiscount(Double discount);
    List<Promotion> findByStartTimeAfter(LocalDate startDate);
    List<Promotion> findByEndTimeBefore(LocalDate endDate);
    List<Promotion> findByStartTimeAfterAndEndTimeBefore(LocalDate startDate, LocalDate endDate);
}

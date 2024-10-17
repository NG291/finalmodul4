package com.finalexam4.service;

import com.finalexam4.model.Promotion;
import com.finalexam4.repository.IPromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PromotionService implements IPromotionService {
    @Autowired
    private IPromotionRepository promotionRepository;

    @Override
    public Iterable<Promotion> findAll() {
        return promotionRepository.findAll();
    }

    @Override
    public Optional<Promotion> findById(Long id) {
        return promotionRepository.findById(id);
    }

    @Override
    public Promotion save(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    @Override
    public void remove(Long id) {
        promotionRepository.deleteById(id);
    }

    @Override
    public List<Promotion> findByCodeContainingAndDiscount(String code, Double discount,LocalDate startDate, LocalDate endDate) {
        if (code != null && discount != null) {
            return promotionRepository.findByCodeContainingAndDiscount(code, discount);
        } else if (code != null) {
            return promotionRepository.findByCodeContaining(code);
        } else if (discount != null) {
            return promotionRepository.findByDiscount(discount);
        } else if (startDate != null && endDate != null) {
            return promotionRepository.findByStartTimeAfterAndEndTimeBefore(startDate, endDate);
        } else if (startDate != null) {
            return promotionRepository.findByStartTimeAfter(startDate);
        } else if (endDate != null) {
            return promotionRepository.findByEndTimeBefore(endDate);
        } else {
            return promotionRepository.findAll(); // Nếu không có điều kiện nào, trả về tất cả
        }
    }

}

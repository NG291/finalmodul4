package com.finalexam4.controller;

import com.finalexam4.model.Promotion;
import com.finalexam4.service.IPromotionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/promotions")
public class PromotionController {
    @Autowired
    IPromotionService promotionService;
  @GetMapping
    public ModelAndView showList(
            @RequestParam(required = false) String code,
            @RequestParam(required = false) Double discount,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate) {

        ModelAndView modelAndView = new ModelAndView("list");
        Iterable<Promotion> promotions = promotionService.findByCodeContainingAndDiscount(code, discount, startDate, endDate);

        modelAndView.addObject("promotions", promotions);
        modelAndView.addObject("keyword", code);
        modelAndView.addObject("discount", discount);
        modelAndView.addObject("startDate", startDate);
        modelAndView.addObject("endDate", endDate);

        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create(){
        ModelAndView modelAndView= new ModelAndView("/create");
        modelAndView.addObject("promotion", new Promotion());
        return modelAndView;
    }
    @PostMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute("promotion") Promotion promotion,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("/create");
            return modelAndView;
        }
        promotionService.save(promotion);
        return new ModelAndView("redirect:/promotions");
    }
    @GetMapping("/{id}/update")
    public ModelAndView update(@PathVariable("id") Long id){
        ModelAndView modelAndView= new ModelAndView("update");
        Optional<Promotion> promotion = promotionService.findById(id);
        modelAndView.addObject("promotion", promotion.get());
        return modelAndView;
    }
    @PostMapping("/update")
    public ModelAndView update(@Valid @ModelAttribute("promotion") Promotion promotion,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("/create");
            return modelAndView;
        }
        promotionService.save(promotion);
        return new ModelAndView("redirect:/promotions");
    }

    @GetMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable("id") Long id){
        ModelAndView modelAndView= new ModelAndView("/delete");
        Optional<Promotion> promotion = promotionService.findById(id);
        modelAndView.addObject("promotion", promotion.get());
        return modelAndView;
    }
    @PostMapping("/delete")
    public ModelAndView delete(Promotion promotion){
        ModelAndView modelAndView = new ModelAndView("redirect:/promotions");
        promotionService.remove(promotion.getId());
        return modelAndView;
    }
}

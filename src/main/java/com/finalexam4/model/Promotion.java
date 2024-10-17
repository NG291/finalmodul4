package com.finalexam4.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
@Entity
@Table(name="promotions")
@Data
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Mã khuyến mãi không được để trống")
    private String code;

    @NotNull
    private LocalDate startTime;
    @NotNull
    private LocalDate endTime;
    @NotNull
    @Min(value = 10000, message = "Giảm giá phải lớn hơn hoặc bằng 10000")
    private Double discount;

    private String detail;

    @AssertTrue(message = "Ngày bắt đầu phải lớn hơn ngày kết thúc ít nhất 1 ngày")
    public boolean isStartTimeBeforeEndTime() {
        return startTime != null && endTime != null && startTime.plusDays(1).isBefore(endTime);
    }

}


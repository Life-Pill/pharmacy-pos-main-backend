package com.lifepill.possystem.dto.responseDTO;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DailySalesSummaryDTO {
    private LocalDate date;
    private long orders;
    private double sales;

}

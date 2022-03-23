package com.example.appspringhomework241.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private Integer fromCardId;

    private Integer toCardId;

    private Double amount;
}

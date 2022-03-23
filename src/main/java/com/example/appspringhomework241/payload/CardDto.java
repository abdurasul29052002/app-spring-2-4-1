package com.example.appspringhomework241.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {
    @NotNull
    private String number;
    @NotNull
    private String expireDate;
    @NotNull
    private Double balance;
    @NotNull
    private Integer userId;
    private boolean active=true;
}

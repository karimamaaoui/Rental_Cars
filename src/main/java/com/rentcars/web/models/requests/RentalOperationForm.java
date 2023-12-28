package com.rentcars.web.models.requests;

import java.time.LocalDate;

import com.rentcars.dao.entities.Cars;
import com.rentcars.dao.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RentalOperationForm {

    private Cars car;
    private User client;
    private LocalDate startDate;
    private LocalDate endDate;
    private String guaranteeType;
    private double rentalFee;
    private String paymentMethod;
    private double guaranteeAmount;

}

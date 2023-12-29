package com.rentcars.web.models.requests;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private String guaranteeType;
    private double rentalFee;
    private String paymentMethod;
    private double guaranteeAmount;

}

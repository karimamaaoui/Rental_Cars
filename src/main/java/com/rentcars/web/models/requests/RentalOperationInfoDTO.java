package com.rentcars.web.models.requests;

import java.util.Date;

import com.rentcars.dao.entities.Cars;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RentalOperationInfoDTO {
	private Cars car;
    private Date startDate;
    private Date endDate;
}

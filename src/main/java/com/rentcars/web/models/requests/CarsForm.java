package com.rentcars.web.models.requests;

import com.rentcars.dao.entities.EState;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarsForm {
	private String number;
	private String model;
    private String brand;
    private float price;
    private String picture;
    private EState state;

}

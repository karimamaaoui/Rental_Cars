package com.rentcars.web.models.requests;

import java.time.LocalDate;
import java.util.List;

import com.rentcars.dao.entities.EState;
import com.rentcars.dao.entities.User;

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
    private User agent;
	
}

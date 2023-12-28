package com.rentcars.buisness.service;

import java.util.List;

import com.rentcars.dao.entities.Cars;


public interface ICarsService {

	    void saveCars(Cars car);
	    Cars getCarsById(long id);
	    void deleteCarsById(long id);
	    void updateCars(Cars car);
	    List<Cars> getAllCars();

}

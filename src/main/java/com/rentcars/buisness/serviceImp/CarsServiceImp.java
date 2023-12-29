package com.rentcars.buisness.serviceImp;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentcars.buisness.service.ICarsService;
import com.rentcars.dao.entities.Cars;
import com.rentcars.dao.repositories.CarsRepository;


@Service
public class CarsServiceImp implements ICarsService{

	@Autowired
	CarsRepository carsRepository;
	
	
	@Override
	public List<Cars> getAllCars() {
		return this.carsRepository.findAll();
	}


	@Override
	public void saveCars(Cars car) {
        this.carsRepository.save(car);

	}


	@Override
	public Cars getCarsById(long id) {
		   Optional <Cars> optional = carsRepository.findById(id);
		   Cars car = null;
	        if (optional.isPresent()) {
	            car = optional.get();
	        } else {
	            throw new RuntimeException(" Car not found for id :: " + id);
	        }
	        return car;
	}


	@Override
	public void deleteCarsById(long id) {
        this.carsRepository.deleteById(id);
		
	}


		@Override
		public void updateCars(Cars car) {
		    Cars existingCar = carsRepository.findById(car.getIdCar()).orElse(null);
		    if (existingCar != null) {
		        existingCar.setBrand(car.getBrand());
		        existingCar.setModel(car.getModel());
		        existingCar.setNumber(car.getNumber());
		        existingCar.setPicture(car.getPicture());
		        existingCar.setPrice(car.getPrice());
		        carsRepository.save(existingCar);
		    }
		}


		

}

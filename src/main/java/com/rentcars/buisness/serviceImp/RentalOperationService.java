package com.rentcars.buisness.serviceImp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentcars.buisness.service.IRentalOperationService;
import com.rentcars.dao.entities.Cars;
import com.rentcars.dao.entities.RentalOperation;
import com.rentcars.dao.repositories.CarsRepository;
import com.rentcars.dao.repositories.RentalOperationRepository;
import com.rentcars.web.models.requests.RentalOperationInfoDTO;

@Service
public class RentalOperationService implements IRentalOperationService {

    @Autowired
    private RentalOperationRepository rentalOperationRepository;

    @Autowired
    private CarsRepository carsRepository;
    @Override
    public List<RentalOperation> getAllRentalOperations() {
        return rentalOperationRepository.findAll();
    }

    @Override
    public void saveRentalOperation(RentalOperation rentalOperation) {
    	Long carId = rentalOperation.getCar().getIdCar();
 	    Cars car = carsRepository.findById(carId).orElse(null);
 	    if(car !=null) {
 	    	rentalOperation.setCar(car);
        rentalOperationRepository.save(rentalOperation);
 	    }
    }

    @Override
    public RentalOperation getRentalOperationById(Long id) {
        Optional<RentalOperation> optional = rentalOperationRepository.findById(id);
        return optional.orElse(null);
    }

	@Override
	public RentalOperation getRentalOperationById(long id) {
		   Optional <RentalOperation> optional = rentalOperationRepository.findById(id);
		   RentalOperation rental = null;
	        if (optional.isPresent()) {
	            rental = optional.get();
	        } else {
	            throw new RuntimeException(" rental not found for id :: " + id);
	        }
	        return rental;

	}

	



   


}

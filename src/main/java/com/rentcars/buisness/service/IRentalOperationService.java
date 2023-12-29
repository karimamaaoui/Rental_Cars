package com.rentcars.buisness.service;

import com.rentcars.dao.entities.Cars;
import com.rentcars.dao.entities.RentalOperation;
import com.rentcars.web.models.requests.RentalOperationInfoDTO;

import java.util.Date;
import java.util.List;

public interface IRentalOperationService {

    List<RentalOperation> getAllRentalOperations();

    RentalOperation getRentalOperationById(Long id);

    void saveRentalOperation(RentalOperation rentalOperation);
    
    RentalOperation getRentalOperationById(long id);
	  
}

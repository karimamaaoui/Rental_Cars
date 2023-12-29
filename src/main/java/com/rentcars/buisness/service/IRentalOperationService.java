package com.rentcars.buisness.service;

import com.rentcars.dao.entities.RentalOperation;

import java.util.Date;
import java.util.List;

public interface IRentalOperationService {

    List<RentalOperation> getAllRentalOperations();

    RentalOperation getRentalOperationById(Long id);

    void saveRentalOperation(RentalOperation rentalOperation);
    boolean isCarAvailable(Long carId, Date startDate, Date endDate) ;

    
}

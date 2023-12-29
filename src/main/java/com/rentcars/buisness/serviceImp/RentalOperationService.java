package com.rentcars.buisness.serviceImp;

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

    public boolean isCarAvailable(Long carId, Date startDate, Date endDate) {
        Cars car = new Cars();
        car.setIdCar(carId);

        List<RentalOperation> overlappingRentals = rentalOperationRepository.findByCarAndStartDateBeforeAndEndDateAfter(
                car, endDate, startDate);

        return overlappingRentals.isEmpty();
    }
   /* @Override
    public boolean isCarAvailableForPeriod(Date endDate, Date startDate) {
        long diffInMillies = Math.abs(endDate.getTime() - startDate.getTime());
        long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        return diffInDays > 0;
    }*/
}

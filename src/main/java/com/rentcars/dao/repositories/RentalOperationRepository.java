package com.rentcars.dao.repositories;

import com.rentcars.dao.entities.Cars;
import com.rentcars.dao.entities.RentalOperation;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RentalOperationRepository extends JpaRepository<RentalOperation, Long> {

}
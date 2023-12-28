package com.rentcars.dao.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rentcars.dao.entities.Cars;


@Repository
public interface CarsRepository extends JpaRepository<Cars, Long>{

}

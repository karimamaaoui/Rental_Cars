package com.rentcars.dao.entities;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RentalOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Cars car;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    private LocalDate startDate;
    private LocalDate endDate;
    private String guaranteeType;
    private double rentalFee;
    private String paymentMethod;
    private double guaranteeAmount;

}

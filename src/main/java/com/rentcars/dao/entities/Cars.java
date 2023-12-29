package com.rentcars.dao.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="cars")

public class Cars {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCar;
	@Column(name = "number", length = 10, unique = true)
	private String number;
    @Column(name = "model", length = 30, nullable = false)
	private String model;
    @Column(name = "brand", length = 30, nullable = false)
    private String brand;
    @Column(name = "price", nullable = false)
    private float price;   
    @Column(name = "picture", length = 255, nullable = true)
    private String picture;
    @Enumerated(EnumType.STRING)
	private EState state;
    
    @ManyToOne
    @JoinColumn(name = "agent_id") 
    private User agent;
	

	
}
package com.rentcars.web.controllers;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.rentcars.buisness.service.ICarsService;
import com.rentcars.buisness.service.IUserService;
import com.rentcars.dao.entities.Cars;
import com.rentcars.dao.entities.EState;
import com.rentcars.dao.entities.User;
import com.rentcars.security.CustomUserDetail;
import com.rentcars.web.models.requests.CarsForm;

import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;


@Controller
@RequestMapping("/cars")
public class CarsController {

	@Autowired
	ICarsService carsService;
	
	@Autowired
	IUserService userService;
	
    // Retrieve all cars
    @GetMapping("/list")
    public  String getAllCars(Model model) {
        model.addAttribute("listCars", carsService.getAllCars());
        return "carslist";
   
    }
    
    
    @GetMapping("/delete-car/{id}")
    @PreAuthorize("hasAuthority('AGENT')")
    public String deleteCars(@PathVariable("id") Long id) {
        Cars carRemove = carsService.getCarsById(id);
        if (carRemove != null) {
        	carsService.deleteCarsById(id);
        }
     
        return "redirect:/cars/list";
    }


    
    @GetMapping("/create-car")
    @PreAuthorize("hasAuthority('AGENT')")
    public String showAddCars(Model model){
        model.addAttribute("carsForm", new CarsForm());
        return "add-car";
    }


    @PostMapping("/save-car")
    @PreAuthorize("hasAuthority('AGENT')")
    public String saveCar(@Valid @ModelAttribute("carsForm") CarsForm carForm,
                      BindingResult result,
                      @RequestParam("file") MultipartFile file) throws IOException {
    if (result.hasErrors()) {
        return "add-car";
    }
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
    Long userId = customUserDetail.getUserId();

    System.out.println(userId);
    User agent = userService.getUserById(userId);
    String uploadDirectory ="C:\\Users\\user\\Documents\\workspace-spring-tool-suite-4-4.20.0.RELEASE\\rentcars\\src\\main\\resources\\static\\images";
    
    if (!file.isEmpty()) {
        String originalFilename = file.getOriginalFilename();
        String filePath = Paths.get(uploadDirectory, originalFilename).toString();

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        }

       carForm.setPicture("/images/" + originalFilename);
    

    } 

    Cars car = new Cars();
    car.setBrand(carForm.getBrand());
    car.setModel(carForm.getModel());
    car.setNumber(carForm.getNumber());
    car.setPrice(carForm.getPrice());
    car.setPicture(carForm.getPicture());
    car.setAgent(agent);
    carsService.saveCars(car);
    return "redirect:/cars/list";
}

    
    @GetMapping("/edit-car/{id}")
    @PreAuthorize("hasAuthority('AGENT')")
    public String showEditCarForm(@PathVariable("id") Long id, Model model) {
        Cars car = carsService.getCarsById(id);
        if(car != null) {
            model.addAttribute("carsForm", new CarsForm(car.getNumber(), car.getModel(), car.getBrand(), car.getPrice(), car.getPicture(),car.getAgent()));
        }

        model.addAttribute("carsForm", car);
        model.addAttribute("idCar", id);
        return "edit-car";
    }


    @PostMapping("/update-car/{id}")
    @PreAuthorize("hasAuthority('AGENT')")
    public String updateCar(@PathVariable("id") Long id, @Valid @ModelAttribute("carsForm") CarsForm carsForm, BindingResult result,
                            @RequestParam("file") MultipartFile file) throws IOException {
        if (result.hasErrors()) {
            return "edit-car";
        }

        // Retrieve the existing car entity
        Cars car = carsService.getCarsById(id);
        if (car != null) {
            car.setBrand(carsForm.getBrand());
            car.setNumber(carsForm.getNumber());
            car.setModel(carsForm.getModel());
            car.setPrice(carsForm.getPrice());
           // car.setState(carsForm.getState());

            // Check if a new file is provided
            if (!file.isEmpty()) {
                String uploadDirectory = "C:\\Users\\user\\Documents\\workspace-spring-tool-suite-4-4.20.0.RELEASE\\rentcars\\src\\main\\resources\\static\\images";
                String originalFilename = file.getOriginalFilename();
                String filePath = Paths.get(uploadDirectory, originalFilename).toString();

                try (InputStream inputStream = file.getInputStream()) {
                    Files.copy(inputStream, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
                }

                // Set the new picture
                car.setPicture(originalFilename);
            }
        }

        // Update the car entity
        carsService.updateCars(car);

        return "redirect:/cars/list";
    }


    
    
    

  
}

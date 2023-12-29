package com.rentcars.web.controllers;

import com.rentcars.buisness.service.ICarsService;
import com.rentcars.buisness.service.IRentalOperationService;
import com.rentcars.dao.entities.Cars;
import com.rentcars.dao.entities.RentalOperation;
import com.rentcars.web.models.requests.RentalOperationForm;

import jakarta.validation.Valid;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rental-operations")
public class RentalOperationController {

    @Autowired
    private IRentalOperationService rentalOperationService;

    @Autowired
    private ICarsService carsService;

    
    @GetMapping("/list")
    public String getAllRentalOperations(Model model) {
        model.addAttribute("rentalOperations", rentalOperationService.getAllRentalOperations());
        return "rentalOperationsList";
    }

    public boolean verifyDate(Long idrent, Date startDate, Date endDate) {
    	     
    	List<RentalOperation> existingOperations = rentalOperationService.getAllRentalOperations();

        for (RentalOperation existingOperation : existingOperations) {
            if (startDate.before(existingOperation.getEndDate()) && endDate.after(existingOperation.getStartDate())) {
                return false;
            }
        }

        return true;
    }
    

    @GetMapping("/verify/{id}")
    public String verify(@PathVariable Long id, Model model) {
        RentalOperation rental = rentalOperationService.getRentalOperationById(id);
        model.addAttribute("rental", rental);

        return "availability";
    }
    
    @GetMapping("/verify-date")
    public String checkDate(
            @RequestParam(name = "rentalId") Long idrent,
            @RequestParam(name = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(name = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
             Model model) {

        if (!verifyDate(idrent, startDate, endDate)) {
            model.addAttribute("errorMessage", "Date overlap error message");

            return "noavailable";
        }

        return "successavailable";
    }
    
    @GetMapping("/add-operation/{carId}")
    public String showAddOperation(@PathVariable Long carId, Model model) {
        Cars selectedCar = carsService.getCarsById(carId);
        model.addAttribute("selectedCar", selectedCar);

        RentalOperationForm rentalOperationForm = new RentalOperationForm();
        rentalOperationForm.setCar(selectedCar);
        model.addAttribute("rentalOperationForm", rentalOperationForm);

        return "addRentalOperation";
    }

    @PostMapping("/save-operation")
    public String saveOperation(
            @Valid @ModelAttribute("rentalOperationForm") RentalOperationForm rentalOperationForm,
            BindingResult result) {
        if (result.hasErrors()) {
            return "client-page";
        }

        
        RentalOperation rentalOperation = new RentalOperation();
        
        rentalOperation.setCar(rentalOperationForm.getCar());
        rentalOperation.setStartDate(rentalOperationForm.getStartDate());
        rentalOperation.setEndDate(rentalOperationForm.getEndDate());
        rentalOperation.setGuaranteeType(rentalOperationForm.getGuaranteeType());
        rentalOperation.setRentalFee(rentalOperationForm.getRentalFee());
        rentalOperation.setPaymentMethod(rentalOperationForm.getPaymentMethod());
        rentalOperation.setGuaranteeAmount(rentalOperationForm.getGuaranteeAmount());

        rentalOperationService.saveRentalOperation(rentalOperation);
        return "redirect:/client-page";
    }
    
    
    

    
    
}

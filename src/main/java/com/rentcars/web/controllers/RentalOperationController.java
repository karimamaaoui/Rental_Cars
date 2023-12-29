package com.rentcars.web.controllers;

import com.rentcars.buisness.service.ICarsService;
import com.rentcars.buisness.service.IRentalOperationService;
import com.rentcars.buisness.service.IUserService;
import com.rentcars.dao.entities.Cars;
import com.rentcars.dao.entities.RentalOperation;
import com.rentcars.dao.entities.User;
import com.rentcars.security.CustomUserDetail;
import com.rentcars.web.models.requests.RentalOperationForm;

import jakarta.validation.Valid;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private IUserService userService;
    
    @GetMapping("/list")
    public String getAllRentalOperations(Model model) {
        model.addAttribute("rentalOperations", rentalOperationService.getAllRentalOperations());
        return "rentalOperationsList";
    }
    /*
    @GetMapping("/add-operation")
    public String showAddOperation(@RequestParam(name = "carId") Long carId, Model model) {
        Cars selectedCar = carsService.getCarsById(carId);
        model.addAttribute("selectedCar", selectedCar);
        //System.out.println("select "+selectedCar.getIdCar());
        model.addAttribute("rentalOperationForm", new RentalOperationForm());
        return "addRentalOperation";
    }


    @PostMapping("/save-operation")
    public String saveOperation(
    		@Valid @ModelAttribute("rentalOperationForm") RentalOperationForm rentalOperationForm,
                                BindingResult result) {
        if (result.hasErrors()) {
            return "addRentalOperation";
        }

        RentalOperation rentalOperation = new RentalOperation();

        
        /*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
        Long userId = customUserDetail.getUserId();


        User user = userService.getUserById(userId);
     
        
      
        //rentalOperation.setClient(user);

        rentalOperation.setStartDate(rentalOperationForm.getStartDate());
        rentalOperation.setEndDate(rentalOperationForm.getEndDate());
        rentalOperation.setGuaranteeType(rentalOperationForm.getGuaranteeType());
        rentalOperation.setRentalFee(rentalOperationForm.getRentalFee());
        rentalOperation.setPaymentMethod(rentalOperationForm.getPaymentMethod());
        rentalOperation.setGuaranteeAmount(rentalOperationForm.getGuaranteeAmount());

        rentalOperationService.saveRentalOperation(rentalOperation);
        return "redirect:/cars/list";
    }
*/
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
            return "addRentalOperation";
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
        return "redirect:/cars/list";
    }
    
    @GetMapping("/checkAvailability")
    public String checkAvailability(
            @RequestParam Long carId,
            @RequestParam Date startDate,
            @RequestParam Date endDate,
            Model model) {

        boolean isAvailable = rentalOperationService.isCarAvailable(carId, startDate, endDate);

        if (isAvailable) {
            model.addAttribute("result", "La voiture est disponible pour la période demandée.");
        } else {
            model.addAttribute("result", "La voiture n'est pas disponible pour la période demandée.");
        }

        return "availability";
    }
}

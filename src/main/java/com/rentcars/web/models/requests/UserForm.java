package com.rentcars.web.models.requests;

import com.rentcars.dao.entities.ERoleUser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private ERoleUser role; 
    }

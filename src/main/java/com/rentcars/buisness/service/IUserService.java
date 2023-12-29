package com.rentcars.buisness.service;

import com.rentcars.dao.entities.User;
import com.rentcars.web.models.requests.UserForm;

public interface IUserService {
	
	User addUser (UserForm userForm);
	 User getUserById(Long userId);

}

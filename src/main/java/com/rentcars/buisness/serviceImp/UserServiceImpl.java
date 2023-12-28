package com.rentcars.buisness.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rentcars.buisness.service.IUserService;
import com.rentcars.dao.entities.ERoleUser;
import com.rentcars.dao.entities.User;
import com.rentcars.dao.repositories.UserRepository;
import com.rentcars.web.models.requests.UserForm;
@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User addUser (UserForm userForm) {

		if(userForm.getRole()==null)
		{
			userForm.setRole(ERoleUser.CLIENT);
			}
		User user = new User(
				null, 
				userForm.getFirstname(),
				userForm.getLastname(),
				userForm.getEmail(), 
				passwordEncoder.encode(userForm.getPassword()) ,
				userForm.getRole());
		return userRepository.save(user);
	}

}

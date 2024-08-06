package com.employee_management.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.employee_management.dto.UserRegistrationDto;
import com.employee_management.service.UserServiceImpl;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

	private UserServiceImpl userServiceImpl;
	
    public UserRegistrationController(UserServiceImpl userServiceImpl) {
		super();
		this.userServiceImpl = userServiceImpl;
	}

	@ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
        userServiceImpl.createUser(registrationDto);
        return "redirect:/registration?success";
    }
}

package com.employee_management.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.employee_management.dto.UserRegistrationDto;
import com.employee_management.entity.User;


public interface UserService extends UserDetailsService{
   User createUser(UserRegistrationDto userDto);
}

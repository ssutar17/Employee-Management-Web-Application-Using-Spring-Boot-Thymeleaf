package com.employee_management.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.employee_management.dto.UserRegistrationDto;
import com.employee_management.entity.Role;
import com.employee_management.entity.User;
import com.employee_management.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	 @Autowired
	 private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public User createUser(UserRegistrationDto userDto) {
		User user= new User(userDto.getFirstName(), userDto.getLastName(),
				userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()),
				Arrays.asList(new Role("ROLE_USER")));
		return userRepository.save(user);
	}
	
	/* "loadUserByUsername" method is overridden method from 
	   "UserDetailsService" interface to load user details from 
	    a data source,typically a database,
	    based on the username (email in this case).  */
	 @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	        User user = userRepository.findByEmail(username);
	        if (user == null) {
	            throw new UsernameNotFoundException("Invalid username or password.");
	        }
	       /* If a user is found, a new "User" object from 
	          "org.springframework.security.core.userdetails" is returned.
	           This object implements the UserDetails interface required 
	           by Spring Security.   */
	        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
	    }

	    /*Converts a collection of Role objects into a collection 
	      of GrantedAuthority objects required by Spring Security 
	      for role-based authorization. */
	    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
	        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	    }

}

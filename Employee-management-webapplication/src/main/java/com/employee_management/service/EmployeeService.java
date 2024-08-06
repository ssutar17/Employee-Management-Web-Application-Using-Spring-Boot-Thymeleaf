package com.employee_management.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.employee_management.entity.Employee;

public interface EmployeeService {
	
	// create
    public Employee createEmployee(Employee emp); 
    
    //getAll
    public List<Employee> getAllEmployees();
    
    //getbyId
    public Employee getEmployeeByid(long id);
    
    //update
    public Employee updateEmployee(Employee emp, long id);
    
    //delete
    public void deleteEmployee(long id);
    
	//pagination
	Page<Employee> findPaginated(int pageNo, int size, String sortField, String sortDirection);
	
}

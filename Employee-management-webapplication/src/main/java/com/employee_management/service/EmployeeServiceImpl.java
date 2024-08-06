package com.employee_management.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.employee_management.entity.Employee;
import com.employee_management.exception.ResourceNotFoundException;
import com.employee_management.repositories.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{

private EmployeeRepository employeeRepository;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	//Create
	@Override
	public Employee createEmployee(Employee emp) {
		return employeeRepository.save(emp);
	}

	//GetAll
	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	//GetByID
	@Override
	public Employee getEmployeeByid(long id) throws ResourceNotFoundException {
		return employeeRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Employee not found for this id :: "+ id));
	}
 
	//update
	@Override
	public Employee updateEmployee(Employee emp, long id) throws ResourceNotFoundException{
		Employee emp1= employeeRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Employee not found for this id :: "+ id));
		
		emp1 = new Employee(emp.getFirstName(), emp.getLastName(), emp.getEmail());
		return employeeRepository.save(emp1);
	}

	//delete
	@Override
	public void deleteEmployee(long id) throws ResourceNotFoundException{
		Employee emp1= employeeRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Employee not found for this id :: "+ id));
	    employeeRepository.delete(emp1);
	}

	//pagination
	@Override
	public Page<Employee> findPaginated(int pageNo, int size, String sortField, String sortDirection) {
       Sort sort= sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) 
                  ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
       
       Pageable pageable= PageRequest.of(pageNo-1, size, sort);
		return this.employeeRepository.findAll(pageable);
	}

}

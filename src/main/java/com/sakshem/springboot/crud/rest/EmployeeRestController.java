package com.sakshem.springboot.crud.rest;

import java.util.List;

import com.sakshem.springboot.crud.entity.Employee;
import com.sakshem.springboot.crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeRestController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}
	// expose "/employees" and return list of employees
	@GetMapping("/employees")
	public List<Employee> findAll() {
		return employeeService.findAll();
	}
	
	// add mapping for GET /employees/{employeeId}
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId) {
		Employee theEmployee = employeeService.findById(employeeId);
		if (theEmployee == null) {
			throw new RuntimeException("Employee id not found - " + employeeId);
		}
		return theEmployee;
	}
	
	// add mapping for POST /employees - add new employee
	@PostMapping("employees")
	public Employee addEmployee(@RequestBody Employee theEmployee) {
		// also just in case they pass an id in JSON ... set id to 0.
		// if the id is 0 it will create a new employee in Database as well.
		// this is to force a save of new item... instead of update
		theEmployee.setId(0);
		employeeService.save(theEmployee);
		return theEmployee;
	}
	// add mapping for PUT /employees - update existing employee
	@PutMapping("employees")
	public Employee updateEmployee(@RequestBody Employee theEmployee) {
		// since employeeId won't be zero therefore it will just update the value
		// if it would have been 0 it will save a new employee
		employeeService.save(theEmployee);
		return theEmployee;
	}
	
	// add mapping for DELETE /employees/{employeeId} - delete employee
	@DeleteMapping("employees/{employeeId}")
	public Employee deleteEmployee(@PathVariable int employeeId) {
		// finding the employee and storing it in a temporary variable for returning it later
		Employee theEmployee = employeeService.findById(employeeId);
		
		// throw exception if null
		if (theEmployee == null) {
			throw new RuntimeException("Employee id not found - " + employeeId);
		}
		// calling employeeService deleteById method to delete the employee.
		employeeService.deleteById(employeeId);
		return theEmployee;
	}
}

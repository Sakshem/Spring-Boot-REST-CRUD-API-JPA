package com.sakshem.springboot.crud.dao;

import java.util.List;

import com.sakshem.springboot.crud.entity.Employee;

public interface EmployeeDAO {
	public List<Employee> findAll();
	
	public Employee findByID(int theId);
	
	public void save(Employee theEmployee);
	
	public void deleteById(int theId);
}

package com.sakshem.springboot.crud.service;

import java.util.List;

import com.sakshem.springboot.crud.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sakshem.springboot.crud.dao.EmployeeDAO;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	private EmployeeDAO employeeDAO;

	// Qualifier annotation
	// Is used to identify which bean I want to create since there are two classes 1) EmployeeDAOHibernateImpl and 2) EmployeeDAOJpaImpl
	// that implements the interface EmployeeDAO
	// therefore we use this annotation to mention which class bean we prefer.
	@Autowired
	public EmployeeServiceImpl(@Qualifier("employeeDAOJpaImpl")EmployeeDAO theEmployeeDAO) {
		employeeDAO = theEmployeeDAO;
	}
	
	@Override
	@Transactional
	public List<Employee> findAll() {
		return employeeDAO.findAll();
	}

	@Override
	@Transactional
	public Employee findById(int theId) {
		return employeeDAO.findByID(theId);
	}

	@Override
	@Transactional
	public void save(Employee theEmployee) {
		employeeDAO.save(theEmployee);
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		employeeDAO.deleteById(theId);
	}

}

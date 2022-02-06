package com.sakshem.springboot.crud.dao;


import com.sakshem.springboot.crud.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOJpaImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public List<Employee> findAll() {
        // Create a query
        Query theQuery = entityManager.createQuery("from Employee");

        // Execute the query and get result list
        List<Employee> employees = theQuery.getResultList();

        // return the results
        return employees;
    }

    @Override
    public Employee findByID(int theId) {
        // get employee
        Employee theEmployee = entityManager.find(Employee.class, theId);

        // return the employee
        return theEmployee;
    }

    @Override
    public void save(Employee theEmployee) {
        // if id == 0 then insert/save else update
        Employee dbEmployee = entityManager.merge(theEmployee);

        // update with id from db ... so we can get generated id for save/insert
        theEmployee.setId(dbEmployee.getId());
    }

    @Override
    public void deleteById(int theId) {
        // delete object with primary key
        Query theQuery = entityManager.createQuery("delete from Employee where id=:employeeId");
        theQuery.setParameter("employeeId", theId);
        theQuery.executeUpdate();
    }
}

package com.sopra.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sopra.modele.Employee;

@Stateless
public class EmployeeService {
	
	@PersistenceContext
	EntityManager em;
	
	public List<Employee> findAll() {
		return em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
	}
	
	public Employee findEmployeeById(int employeeId) {

		return em.find(Employee.class, employeeId);
	}
	
	public Employee createOrUpdate(Employee e) {
		Employee existing = findEmployeeById(e.getId());
		if (existing != null) {
			// update
			existing.setName(e.getName());
			existing.setFirstname(e.getFirstname());
			existing.setAgency(e.getAgency());
			

			return existing;
		} else {
			e.setId(0);
			em.persist(e);

			return e;
		}
	}
	
	public void delete(int id) {
		Employee employee = findEmployeeById(id);
		if (employee != null)
			em.remove(employee);
	}
	
	public List<Employee> search(String name, String firstname) {
		List<Employee> result = new ArrayList<>();

		return result;
	}
}

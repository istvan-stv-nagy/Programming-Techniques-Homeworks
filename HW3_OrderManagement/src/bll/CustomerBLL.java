package bll;

import java.util.ArrayList;
import java.util.List;

import dao.CustomerDAO;
import model.Customer;
import validator.NameValidator;
import validator.TelValidator;
import validator.Validator;

/**
 * Business Logic Class which uses the CustomerDAO class to access the database and
 * the validators to validate the user input. It contains customer specific operations.
 * @author Istvan
 *
 */

public class CustomerBLL {
	private CustomerDAO customerDAO;
	private List<Validator<Customer>> validators;
	
	public CustomerBLL() {
		customerDAO = new CustomerDAO();
		validators=new ArrayList<Validator<Customer>>();
		validators.add(new TelValidator());
		validators.add(new NameValidator());
	}
	
	/**
	 * insers a new customer to the database
	 * @param customer the customer to be inserted
	 */
	public void insert(Customer customer) {
		for (Validator<Customer> v : validators) {
			if (!v.isValid(customer)) {
				throw new IllegalArgumentException("Invalid argument identified by " + v.getClass().getName());
			}
		}
		customerDAO.insert(customer);
	}
	
	/**
	 * edits an already existing customer
	 * @param id id of the customer which uniquely identifies it
	 * @param customer the customer object which replaces the old one
	 */
	public void edit(int id, Customer customer) {
		for (Validator<Customer> v : validators) {
			if (!v.isValid(customer)) {
				throw new IllegalArgumentException("Invalid argument identified by " + v.getClass().getName());
			}
		}
		customerDAO.edit(id, customer);
	}
	
	/**
	 * deletes a customer from database
	 * @param id id of customer
	 */
	public void delete(int id) {
		customerDAO.delete(id);
	}
	
	/**
	 * list all the customers in the database
	 * @return a list of objects containing all the customers
	 */
	public List<Object> list() {
		return customerDAO.list();
	}
	
	/**
	 * find a customer of a given name
	 * @param name name of the customer to be searched
	 * @return a list of customers with the given name
	 */
	public List<Object> findByName(String name) {
		if (!validators.get(1).isValid(new Customer(0,name,"")))
			throw new IllegalArgumentException("Invalid name!");
		return customerDAO.findByName(name);
	}
}

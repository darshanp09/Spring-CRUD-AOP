package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomer() {
		
		// Get the current session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query  : from or.hirbernate.query // 
		
		Query<Customer> theQuery = currentSession.createNamedQuery("Customer.findAll",Customer.class);
		
		// execute query and result the list 
		List<Customer> cRef = theQuery.getResultList();
		//return the result 
		return cRef;
	}

	@Override
	public void saveCustomer(Customer customer) {
		// Get the hibernate current session //
		Session session = sessionFactory.getCurrentSession();
		
		// Save the current session 
		session.saveOrUpdate(customer);
		
	}

	@Override
	public Customer getCustomer(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		
		Customer theCustomer = session.get(Customer.class, id);
		
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int id) {
		
	Session session = sessionFactory.getCurrentSession();
	
	Query query = session.createQuery("delete from Customer where id=:customerId");
	
	query.setParameter("customerId", id);
	
	query.executeUpdate();
	
		
	}

	@Override
	public List<Customer> searchCustomers(String theSearchName) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Customer> theQuery = null;
		
		//
		// only search by name if theSearchName is not empty
		//
		if (theSearchName != null && theSearchName.trim().length() > 0) {

			// search for firstName or lastName ... case insensitive
			theQuery =currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName order by firstName", Customer.class);
			theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

		}
		else {
			// theSearchName is empty ... so just get all customers
			
			theQuery = currentSession.createNamedQuery("Customer.findAll",Customer.class);
		}
		
		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();
				
		// return the results		
		return customers;
	}
	

}

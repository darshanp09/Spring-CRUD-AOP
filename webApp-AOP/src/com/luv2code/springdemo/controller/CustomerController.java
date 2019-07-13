package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	private Customer customer;
	
	
	@GetMapping("/list")
	public String listCustomer(Model model) {
		
		// get the customer from DAO
		List<Customer> customer = customerService.getCustomer();
		
		model.addAttribute("customers",customer);
		
		return "list-customer";
	}
	
	
	@GetMapping("/showFromForAdd")
	public String showFormForAdd(Model model) {
		
		customer = new Customer();
		model.addAttribute("customer",customer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer cRef) {
		
		customerService.saveCustomer(cRef);
		
		return "redirect:/customer/list";
		
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int id, Model model) {
		
		customer = customerService.getCustomer(id);
		
		model.addAttribute("customer",customer);
		
		return "customer-form";
		
	}
	
	@GetMapping("/showFormForDelete")
	public String showFormForDelete(@RequestParam("customerId") int id) {
		
		customerService.deleteCustomer(id);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/search")
	public String searchCustomers(@RequestParam("theSearchName") String theSearchName,
									Model theModel) {

		// search customers from the service
		List<Customer> theCustomers = customerService.searchCustomers(theSearchName);
				
		// add the customers to the model
		theModel.addAttribute("customers", theCustomers);

		return "list-customer";		
	}
	
}

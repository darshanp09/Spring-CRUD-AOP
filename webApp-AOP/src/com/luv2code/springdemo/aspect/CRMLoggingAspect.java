package com.luv2code.springdemo.aspect;

import java.util.List;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.luv2code.springdemo.entity.Customer;

@Aspect
@Component
public class CRMLoggingAspect {
	// setup Logger
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	// setup pointCut declarations
	@Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
	public void froControllerPackage() {}
	
	// do the same for the service and dao
	@Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
	public void froServicePackage() {}
	
	@Pointcut("execution(* com.luv2code.springdemo.dao.*.*(..))")
	public void froDaoPackage() {}
	
	@Pointcut("froControllerPackage() || froServicePackage() || froDaoPackage()")
	public void forAppFlow() {}
	
	
	// add @ before advice
	
	@Before("forAppFlow()")
	public void before(JoinPoint joinPoint) {
		
		String method = joinPoint.getSignature().toShortString();
		myLogger.info("=====>>> @Before : Calling Method : "+method);
		
		
		//get the arguments
		
		Object[] args= joinPoint.getArgs();
		
		//loop thru and display the args[]
		for(Object tempArgs: args) {
			myLogger.info("=====>>> Arguments : "+tempArgs);
		}
		
	}
	
	
	@AfterReturning(
			pointcut="forAppFlow()",
			returning="result"
			)
	public void afterReturning(JoinPoint joinPoint,Object result) {
		
		// display the method we are returning from 
		String method = joinPoint.getSignature().toShortString();
		myLogger.info("=====>>> @AfterReturning : Calling Method : "+method);
		
		// display the data returned 
		myLogger.info("=====>>> Result : "+result);
		
		
	}
	
	
/*	@AfterReturning(
			pointcut="forAppFlow()",
			returning="result"
			)
	public void afterReturning(JoinPoint joinPoint, List<Customer> result) {
		
		// display the method we are returning from 
		String method = joinPoint.getSignature().toShortString();
		myLogger.info("=====>>> @AfterReturning : Calling Method : "+method);
		
		// Playing Around
		convertAccountToUpperCase(result);
		
		// display the data returned 
		myLogger.info("=====>>> Result : "+result);
		
		
	}

	private void convertAccountToUpperCase(List<Customer> result) {
		
		for(Customer cRef : result) {
			String upperCase = cRef.getFirstName().toUpperCase();
			String lastUpper = cRef.getLastName().toUpperCase();
			String emailUpper = cRef.getEmail().toUpperCase();
			cRef.setFirstName(upperCase);
			cRef.setLastName(lastUpper);
			cRef.setEmail(emailUpper);
		}
		
	}  */
	
	
	

}

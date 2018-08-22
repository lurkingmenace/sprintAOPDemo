package com.jdivirgilio.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.jdivirgilio.aopdemo.dao.AccountDAO;
import com.jdivirgilio.aopdemo.dao.MembershipDAO;

public class AfterReturningDemoApp {

	public static void main(String[] args) {

		// Read spring config java class
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// Get the bean from the container
		AccountDAO accountDAO = 
				context.getBean("accountDAO", AccountDAO.class);
		
		List<Account> accounts = accountDAO.findAccounts();

		System.out.println("AfterReturningDempApp");
		System.out.println("---");
		System.out.println(accounts);
		System.out.println("\n");
		
		// Close the context
		context.close();
	}
}

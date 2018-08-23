package com.jdivirgilio.aopdemo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.jdivirgilio.aopdemo.dao.AccountDAO;

public class AfterThrowingDemoApp {

	public static void main(String[] args) {

		// Read spring config java class
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// Get the bean from the container
		AccountDAO accountDAO = 
				context.getBean("accountDAO", AccountDAO.class);
		
		List<Account> accounts = new ArrayList<>();
		try {
			accounts = accountDAO.findAccounts(true);
		} catch (Exception e) {
			System.out.println("Program caught Exception: " + e);
		}

		System.out.println("AfterThrowingDemoApp");
		System.out.println("---");
		System.out.println(accounts);
		System.out.println("\n");
		
		// Close the context
		context.close();
	}
}

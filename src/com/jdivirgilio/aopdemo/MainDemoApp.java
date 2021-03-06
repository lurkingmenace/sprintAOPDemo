package com.jdivirgilio.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.jdivirgilio.aopdemo.dao.AccountDAO;
import com.jdivirgilio.aopdemo.dao.MembershipDAO;

public class MainDemoApp {

	public static void main(String[] args) {

		// Read spring config java class
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// Get the bean from the container
		AccountDAO accountDAO = 
				context.getBean("accountDAO", AccountDAO.class);
		MembershipDAO membershipDAO = 
				context.getBean("membershipDAO", MembershipDAO.class);
		
		// Call the business methond
		accountDAO.addAccount();
		accountDAO.doWork();
		
		accountDAO.setName("test");
		accountDAO.setServiceCode("gold");
		
		System.out.println("accountDAO.getName(): " + accountDAO.getName());
		System.out.println("accountDAO.getServiceCode(): " + accountDAO.getServiceCode());
				
		membershipDAO.addAccount();
		membershipDAO.goToSleep();
		
		// Close the context
		context.close();
	}
}

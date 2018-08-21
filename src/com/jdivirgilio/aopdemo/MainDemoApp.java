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
		membershipDAO.addAccount();
		
		// Close the context
		context.close();
	}
}

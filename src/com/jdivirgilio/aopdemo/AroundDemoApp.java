package com.jdivirgilio.aopdemo;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.jdivirgilio.aopdemo.service.TrafficFortuneService;

public class AroundDemoApp {

	public static void main(String[] args) {

		// Read spring config java class
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		TrafficFortuneService tfs = context.getBean("trafficFortuneService", TrafficFortuneService.class);
		
		System.out.println("The fortune is: "
				+ tfs.getFortune(false));
		
		System.out.println("finished");
		// Close the context
		context.close();
	}
}

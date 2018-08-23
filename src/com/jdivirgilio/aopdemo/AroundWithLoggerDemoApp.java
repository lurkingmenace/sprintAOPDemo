package com.jdivirgilio.aopdemo;


import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.jdivirgilio.aopdemo.service.TrafficFortuneService;

public class AroundWithLoggerDemoApp {

	public static void main(String[] args) {
		
		// Setup the logger that spring uses so the output will be consistent
		final Logger logger = Logger.getLogger(AroundWithLoggerDemoApp.class.getName());
		
		
		// Read spring config java class
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		TrafficFortuneService tfs = context.getBean("trafficFortuneService", TrafficFortuneService.class);
		
		logger.info("The fortune is: "
				+ tfs.getFortune());
		
		logger.info("finished");
		// Close the context
		context.close();
	}
}

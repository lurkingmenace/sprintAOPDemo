package com.jdivirgilio.aopdemo.service;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

@Component
public class TrafficFortuneService {

	
	public String getFortune(boolean forceException) throws RuntimeException {
		
		// simulate a delay
		try {
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
				
		if (forceException) {
			throw new RuntimeException("All Clear!");
		}
		else {
			// return the fortune
			return "Take public transit!";
		}
		
	}
	
	
}

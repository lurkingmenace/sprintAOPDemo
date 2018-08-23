package com.jdivirgilio.aopdemo.service;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

@Component
public class TrafficFortuneService {

	
	public String getFortune() {
		
		// simulate a delay
		try {
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
				
		// return the fortune
		return "Take public transit!";
		
	}
	
	
}

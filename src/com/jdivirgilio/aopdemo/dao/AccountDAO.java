package com.jdivirgilio.aopdemo.dao;

import org.springframework.stereotype.Component;

@Component
public class AccountDAO {

	public void addAccount() {
		
		System.out.println(getClass() + ": addAcount()");
	}
	
	public boolean doWork() {
		System.out.println(getClass() + ": doWork()");
		return true;
	}
}

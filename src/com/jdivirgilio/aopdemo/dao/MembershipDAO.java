package com.jdivirgilio.aopdemo.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {

	public void addAccount() {
		System.out.println(getClass() + ": addAccount()");
	}
	
	public boolean goToSleep() {
		System.out.println(getClass() + ": goToSleep()");
		return true;
	}
}

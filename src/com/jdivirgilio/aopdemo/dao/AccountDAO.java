package com.jdivirgilio.aopdemo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jdivirgilio.aopdemo.Account;

@Component
public class AccountDAO {
	
	private String name;
	private String serviceCode;

	public String getName() {
		System.out.println(getClass() + ": getName()");
		return name;
	}

	public void setName(String name) {
		System.out.println(getClass() + ": setName()");
		this.name = name;
	}

	public String getServiceCode() {
		System.out.println(getClass() + ": getServiceCode()");
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		System.out.println(getClass() + ": setServiceCode()");
		this.serviceCode = serviceCode;
	}

	public void addAccount() {
		
		System.out.println(getClass() + ": addAcount()");
	}
	
	public boolean doWork() {
		System.out.println(getClass() + ": doWork()");
		return true;
	}
	
	public List<Account> findAccounts() {
		
		List<Account> accounts = new ArrayList<>();
		
		// create sample acounts
		Account account = new Account("John", "gold");
		Account account1 = new Account("Mary", "silver");
		Account account2 = new Account("Pete", "bronze");
		
		// Add to list
		accounts.add(account);
		accounts.add(account1);
		accounts.add(account2);
		
		return accounts;
		
	}
}

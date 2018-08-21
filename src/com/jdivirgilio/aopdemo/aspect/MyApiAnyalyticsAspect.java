package com.jdivirgilio.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(20)
public class MyApiAnyalyticsAspect {

	@Before("AopExpressions.forDaoPackage()")
	private void performApiAnalytics() {
		System.out.println(getClass() + ":performApiAnalytics()");
	}
	
}

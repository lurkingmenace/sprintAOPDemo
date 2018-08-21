package com.jdivirgilio.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyApiAnyalyticsAspect {

	@Before("AopExpressions.forDaoPackageNoGetterSetter()")
	private void performApiAnalytics() {
		System.out.println(getClass() + ":performApiAnalytics()");
	}
	
}

package com.jdivirgilio.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyCloudLogAsyncAspect {
	
	@Before("AopExpressions.forDaoPackageNoGetterSetter()")
	private void logToCloud() {
		System.out.println(getClass() + ":logToCloud()");
	}

}

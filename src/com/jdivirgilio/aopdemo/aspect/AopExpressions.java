package com.jdivirgilio.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopExpressions {

	// Point cut expression declaration. Used to avoid multiple C&P of the same point cut expression
	@Pointcut("execution(* com.jdivirgilio.aopdemo.dao.*.*(..))")
	public void forDaoPackage() {}
	
		// Create a pointcut for getters
	@Pointcut("execution(* com.jdivirgilio.aopdemo.dao.*.get*(..))")
	public void getter() {}

	// Create a pointcut for setters
	@Pointcut("execution(* com.jdivirgilio.aopdemo.dao.*.set*(..))")
	public void setter() {}
	
	@Pointcut("forDaoPackage() && !(getter() || setter())")
	public void forDaoPackageNoGetterSetter() {}
}

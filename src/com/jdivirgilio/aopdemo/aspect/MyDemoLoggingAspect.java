package com.jdivirgilio.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

	// Point cut expression declaration. Used to avoid multiple C&P of the same point cut expression
	@Pointcut("execution(* com.jdivirgilio.aopdemo.dao.*.*(..))")
	private void forDaoPackage() {}
	
	// this is where we add all of our related advices for logging
	
	// let's start with an @Before advice
	@Before("forDaoPackageNoGetterSetter()") // The parenthetical exp is a pointcut expression
	private void beforeAddAccountAdvice() {
	  // A  predicate expression for where advice should be applied
	  // An "execution" point cut applies to the execution of a given method
	  // execution(modifiers-pattern? return-type-pattern declaring-type-patter? method-name-pattern(param-pattern) throws-pattern?)
	  // ? types are optional. wildcards * (matches on everything) can be used.
	  // The above @Before leaves off class name. It will match any calls with the sig above
	  // @Before("execution(public void add*())") .. matches all methods starting with add....
	  // @Before("execution(* add*())") .. minimum required w/ wildcards
	  // Parameter Pattern Wildcards: () - no parms
	  //                              (*) - one arg of any type
	  //               				  (..) - 0 or more args of any type
	  //                              The param if specified..must include the fully qualified class name (no var)
	  // matching package would be similar to ("execution(* com.jdivirgilio.aopdemo.*.*(..))")
		System.out.println(getClass() + ": preAddAccount()");
	}
	
	@Before("forDaoPackageNoGetterSetter()")
	private void performApiAnalytics() {
		System.out.println(getClass() + ":performApiAnalytics()");
	}
	
	// Create a pointcut for getters
	@Pointcut("execution(* com.jdivirgilio.aopdemo.dao.*.get*(..))")
	private void getter() {}

	// Create a pointcut for setters
	@Pointcut("execution(* com.jdivirgilio.aopdemo.dao.*.set*(..))")
	private void setter() {}
	
	@Pointcut("forDaoPackage() && !(getter() || setter())")
	private void forDaoPackageNoGetterSetter() {}
}

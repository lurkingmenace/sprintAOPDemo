package com.jdivirgilio.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

	// this is where we add all of our related advices for logging
	
	// let's start with an @Before advice
	@Before("execution(public void addAccount())") // The parenthetical exp is a pointcut expression
	public void preAddAccount() {
	  // A  predicate expression for where advice should be applied
	  // An "execution" point cut applies to the execution of a given method
	  // execution(modifiers-pattern? return-type-pattern declaring-type-patter? method-name-pattern(param-pattern) throws-pattern?)
	  // ? types are optional. wildcards * (matches on everything) can be used.
	  // The above @Before leaves off class name. It will match any calls with the sig above
	  // @Before("execution(public void add*())") .. matches all methods starting with add....
	  // @Before("execution(* add*())") .. minimum required w/ wildcards
		System.out.println(getClass() + ": beforeAddAccountAdvice()");
	}
	
	
}

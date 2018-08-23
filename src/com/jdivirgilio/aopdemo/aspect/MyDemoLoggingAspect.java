package com.jdivirgilio.aopdemo.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.jdivirgilio.aopdemo.Account;

@Aspect
@Component
@Order(10)
public class MyDemoLoggingAspect {

	// this is where we add all of our related advices for logging
	
	// let's start with an @Before advice
	@Before("AopExpressions.forDaoPackage()") // The parenthetical exp is a pointcut expression
	private void beforeAddAccountAdvice(JoinPoint joinPoint) {
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
		
		// display the method sig
		System.out.println("Method: " + joinPoint.getSignature());
		
		// display the method args
		for (Object o : joinPoint.getArgs()) {
			System.out.println("	Arg: " + o);
		}
		
	}
	
	@AfterReturning(pointcut="execution(* com.jdivirgilio.aopdemo.dao.AccountDAO.findAccounts(..))",
			returning="accounts")
	public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> accounts) {
		
		// print out which method we are advising on
		String method = joinPoint.getSignature().toShortString();
		System.out.println("\nExecuting @AfterReturing on method " + method);
		System.out.println("\n    result is " + accounts);
		
		// Post process the data - modify it
		changeAllNamesToUpperCase(accounts);
	}

	private void changeAllNamesToUpperCase(List<Account> accounts) {

		for (Account account: accounts) {
			account.setName(account.getName().toUpperCase());
		}
		
	}
	
	@AfterThrowing(pointcut="execution(* com.jdivirgilio.aopdemo.dao.AccountDAO.findAccounts(..))",
			throwing="e")
	public void afterThrowing(JoinPoint joinPoint, Throwable e) {
		System.out.println("In the @AfterThrowing advice. The exception is: " + e.getMessage());
	}

	@After("execution(* com.jdivirgilio.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyAdvice(JoinPoint joinPoint) {
		System.out.println("In the @After advice. After method: " + joinPoint.getSignature().toShortString());
	}
}

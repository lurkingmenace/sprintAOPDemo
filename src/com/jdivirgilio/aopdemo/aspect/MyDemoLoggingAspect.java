package com.jdivirgilio.aopdemo.aspect;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.jdivirgilio.aopdemo.Account;

@Aspect
@Component
@Order(10)
public class MyDemoLoggingAspect {

	final Logger logger = Logger.getLogger(getClass().getName());
	
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
		logger.info(getClass() + ": preAddAccount()");
		
		// display the method sig
		logger.info("Method: " + joinPoint.getSignature());
		
		// display the method args
		for (Object o : joinPoint.getArgs()) {
			logger.info("	Arg: " + o.toString());
		}
		
	}
	
	@AfterReturning(pointcut="execution(* com.jdivirgilio.aopdemo.dao.AccountDAO.findAccounts(..))",
			returning="accounts")
	public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> accounts) {
		
		// print out which method we are advising on
		String method = joinPoint.getSignature().toShortString();
		logger.info("\nExecuting @AfterReturing on method " + method);
		logger.info("\n    result is " + accounts);
		
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
		logger.info("In the @AfterThrowing advice. The exception is: " + e.getMessage());
	}

	@After("execution(* com.jdivirgilio.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyAdvice(JoinPoint joinPoint) {
		logger.info("In the @After advice. After method: " + joinPoint.getSignature().toShortString());
	}
	
	@Around("execution(* com.jdivirgilio.aopdemo.service.*.getFortune(..))")
	public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		
		// print out the method we are advising on
		logger.info("In @Around advice. Method: " + joinPoint.getSignature().toShortString());
		
		// get begin timestamp
		Calendar startTime = Calendar.getInstance();
		
		// execute the method
		Object o = null;
		
		try {
			o = joinPoint.proceed();
		} catch (Exception e) {
			// Log the exception
			logger.info("@Around caught exception: " + e.getMessage());
			
			// give user a customer message/fortune
			o = "The roads are icey!"; 
			
			// Or we could just rethrow the exception: throw e;
		}
		
		// get the ending timestamp
		Calendar endTime = Calendar.getInstance();
		
		// display the duration
		long diff = endTime.getTimeInMillis() - startTime.getTimeInMillis();
		
		logger.info("In @Around advice. The duration was: " + TimeUnit.MILLISECONDS.toMillis(diff) + " milliseconds");
		
		return o;
	}
}

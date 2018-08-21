package com.jdivirgilio.aopdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.jdivirgilio.aopdemo.dao.AccountDAO;

@Aspect
@Component
@Order(10)
public class MyDemoLoggingAspect2 {

	// this is where we add all of our related advices for logging
	
	// let's start with an @Before advice
	@Before("AopExpressions.forDaoPackageNoGetterSetter()") // The parenthetical exp is a pointcut expression
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
			if (o instanceof AccountDAO) {
				System.out.println("	Arg: " + (o instanceof AccountDAO ? (AccountDAO)o : o));
			}
		}
		
	}

}

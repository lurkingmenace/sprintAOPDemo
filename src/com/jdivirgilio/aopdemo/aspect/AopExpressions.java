package com.jdivirgilio.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopExpressions {

	// Different Advice Types
	/*
	 * Before advice: run before the method
	 * After returning adivce: run after the method (success execution)
	 * After throwing advice: run after method if exception thrown
	 * After finally advice: run after the method but before the method returns (runs after success OR failure [exception])
	 *                       This will run before @AfterThrowing (just like the finally block) THis does not have access to the
	 *                       exception
	 * Around advice: run before and after the method
	 */
		
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

package com.jdivirgilio.aopdemo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration // Pure java configuration (No xml)
@EnableAspectJAutoProxy // Spring AOP Proxy support
@ComponentScan("com.jdivirgilio.aopdemo")  // Component scan over which packages and subs
public class DemoConfig {

	
	
}

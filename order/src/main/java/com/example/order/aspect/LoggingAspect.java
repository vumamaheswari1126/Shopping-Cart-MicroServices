package com.example.order.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	@Before("execution(* com.example.order.service.*.*(..))")
	public void logBeforeMethod(JoinPoint joinPoint)
	{
		System.out.println("Enter into Method "+joinPoint.getSignature().getName());
	}

	@After("execution(* com.example.order.service.*.*(..))")
	public void logAfterMethod(JoinPoint joinPoint)
	{
		System.out.println("Execution Completed "+joinPoint.getSignature().getName());
	}

	@AfterReturning("execution(* com.example.order.service.*.*(..))")
	public void logAfterReturnMethod(JoinPoint joinPoint)
	{
		System.out.println("Execution completed Sucessfully "+joinPoint.getSignature().getName());
	}

	@AfterThrowing("execution (* com.example.order.service.*.*(..))")
	public void logAfterThrownMethod(JoinPoint joinPoint)
	{
		System.out.println("Exception Thrown at "+joinPoint.getSignature().getName());
	}

	@Around("execution(* com.example.order.service.*.*(..))")
	public Object logAroundMethod(ProceedingJoinPoint proceedJoinPoint) throws Throwable
	{
		System.out.println("Before Execution");

		try
		{
			Object res=proceedJoinPoint.proceed();
			return res;

		}
		finally
		{
			System.out.println("After Execution");
		}


	}


}

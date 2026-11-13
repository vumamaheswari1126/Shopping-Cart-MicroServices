package com.example.product.aspect;

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
public class LoggingAspect
{
	@Before("execution(* com.example.product.service.*.*(..))")
	public void logBeforeMethod(JoinPoint joinPoint)
	{
		System.out.println("' Before Aspect ': Product Service method is Entering  "+ joinPoint.getSignature().getName()+" Method");
	}

	@AfterReturning("execution(* com.example.product.service.*.*(..))")
	public void logAfterReturnMethoed(JoinPoint joinPoint)
	{
		System.out.println(" 'After Retrun Aspect ':Product Service Method is Completed "+joinPoint.getSignature().getName()+" Method");
	}

	@After("execution(* com.example.product.service.*.*(..))")
	public void logAfterMethoed(JoinPoint joinPoint)
	{
		System.out.println(" 'After Aspect ':Product Service Method is Completed "+joinPoint.getSignature().getName()+" Method");
	}

	@AfterThrowing("execution(* com.example.product.service.*.*(..))")
	public void logThrownMethod(JoinPoint joinPoint)
	{
		System.out.println(" 'AfterThrown Aspect ':Product Service Method is Thrown Exception in "+joinPoint.getSignature().getName()+" Method");
	}

	@Around("execution(* com.example.product.service.*.*(..))")
	public Object aroundMethod(ProceedingJoinPoint JP) throws Throwable
	{
		System.out.println("Before Execution");
		long start=System.currentTimeMillis();
		try
		{
			Object result=JP.proceed();

		    return result;
		}
		finally
		{
			long end=System.currentTimeMillis();
			System.out.println("After Execution");
			System.out.println("Time "+(end-start)+" ms");

		}


	}

}

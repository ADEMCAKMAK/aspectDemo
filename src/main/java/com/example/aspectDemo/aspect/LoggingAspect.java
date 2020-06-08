package com.example.aspectDemo.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;


/**
 *
 * execution(modifiers-pattern? ret-type-pattern declaring-type-pattern? name-pattern(param-pattern) throws-pattern?)
 *
 */

@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* com.example.aspectDemo.service..*(..)))")
    private void forDoService() {}

    /**
     * run this before.
     * @param joinPoint
     */
    @Before("forDoService()")
    public void doBefore(JoinPoint joinPoint){
        System.out.println("\n=====>>> Executing @Before advice " +
                joinPoint.getSignature().getName());
    }

    /**
     * run this if execution complete normally
     * @param joinPoint
     */
    @AfterReturning("forDoService()")
    public void doAfterReturning(JoinPoint joinPoint) {
        System.out.println("\n=====>>> Executing @AfterReturning advice " +
                joinPoint.getSignature().getName());
    }

    /**
     * run this if any exception throws
     * @param joinPoint
     */
    @AfterThrowing("forDoService()")
    public void doAfterThrowing(JoinPoint joinPoint) {
        System.out.println("\n=====>>> Executing @AfterThrowing advice " +
                joinPoint.getSignature().getName());
    }

    /**
     * run this similiar to finally block.
     * @param joinPoint
     */
    @After("forDoService()")
    public void doAfter(JoinPoint joinPoint) {
        System.out.println("\n=====>>> Executing @After advice " +
                joinPoint.getSignature().getName());
    }

    @Around("forDoService()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
    {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        //Get intercepted method details
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        final StopWatch stopWatch = new StopWatch();

        //Measure method execution time
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();

        System.out.println("\n=====>>> Executing @Around advice " + methodName);

/*
        //Log method execution time
        System.out.println("Execution time of " + className + "." + methodName + " "
                + ":: " + stopWatch.getTotalTimeMillis() + " ms");
*/

        return result;
    }
}

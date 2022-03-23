package com.deloitte.packmov.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Aspect class for logging exceptions and executions
 */
@Component
@Aspect
public class LoggingUtility {
    private static final Logger logger = LogManager.getLogger(LoggingUtility.class);

    /**
     * Logs Service class return values
     * @param joinPoint the join point
     * @param res the return value
     */
    @AfterReturning(pointcut = "execution(* com.deloitte.packmov.daos.*.*(..))", returning = "res")
    public void logServiceReturns(JoinPoint joinPoint, Object res) {
        logger.info("{} completed successfully. Returned {}.", joinPoint.getSignature(), res);
    }

    /**
     * Logs Service class method executions
     * @param joinPoint the join point
     */
    @Before(value = "execution(* com.deloitte.packmov.services.*.*(..))")
    public void logServiceCalls(JoinPoint joinPoint) {
        logger.info("Executing [{}]. Args [{}]", joinPoint.getSignature(), joinPoint.getArgs());
    }

    /**
     * Logs Service class thrown exceptions
     * @param exception the thrown exception
     */
    @AfterThrowing(pointcut = "execution(* com.deloitte.packmov.daos.*.*(..))", throwing = "exception")
    public void logExceptionFromDAO(Exception exception) {
        log(exception);
    }

    /**
     * Logs DAO class thrown exceptions
     * @param exception the thrown exception
     */
    @AfterThrowing(pointcut = "execution(* com.deloitte.packmov.services.*.*(..))", throwing = "exception")
    public void logExceptionFromService(Exception exception) {
        log(exception);
    }

    private void log(Exception exception) {
        logger.error(exception.getMessage(), exception);
    }
}

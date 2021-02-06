/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.FifaRangListServer.aspects;


import fon.silab.FifaRangListServer.exceptions.ApiException;
import fon.silab.FifaRangListServer.exceptions.ApiValidationException;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 *
 * @author Veljko
 */
@Aspect
@Component
public class LoggingAOP {

    private final static Logger LOGGER = Logger.getLogger("Logger");
    
    public LoggingAOP() {
    }
    
    @Pointcut("execution(public * fon.silab.FifaRangListServer.services.*.*(..))")
    private void servicesClasses() {
    }
    
    @Before("servicesClasses()")
    public void beforeServiceClassesLogger(JoinPoint joinPoint) {
        LOGGER.info("Calling service "+joinPoint.getSignature().getName()+"() in service class "+joinPoint.getTarget().getClass().getSimpleName());
    }

    @AfterReturning("servicesClasses()")
    public void afterReturnServiceClassesLogger(JoinPoint joinPoint) {
        LOGGER.info("Succesfully called service "+joinPoint.getSignature().getName()
         +"() in service class "+joinPoint.getTarget().getClass().getSimpleName());
    }

    @AfterThrowing(pointcut = "servicesClasses()", throwing = "ex")
    public void afterThrowServiceClassesLogger(JoinPoint joinPoint, Exception ex) {
        LOGGER.error( "Unsuccesfully called service "+joinPoint.getSignature().getName()+"() in class "
                +joinPoint.getTarget().getClass().getName()+". Reason is "+ex);
    }
    
    @Pointcut("execution(public * fon.silab.FifaRangListServer.controllers.*.*(..))")
    private void controllersClasses() {
    }
    
    @AfterThrowing(pointcut = "controllersClasses()", throwing = "ex")
    public void afterThrowControllersClassesLogger(JoinPoint joinPoint,Exception ex) {
        LOGGER.info( "Exception in REST controller "+joinPoint.getSignature().getName()+"() in class "
                +joinPoint.getTarget().getClass().getName()+". Reason is "+ex);
    }
    
    @Pointcut("execution(protected org.springframework.http.ResponseEntity fon.silab.FifaRangListServer.exceptions.RestExceptionHandler.handleMethodArgumentNotValid(..))")
    private void validationException() {
    }
    
    @AfterReturning(pointcut = "validationException()", returning = "result")
    public void afterValidationException(JoinPoint joinPoint, org.springframework.http.ResponseEntity result) {
        ApiValidationException apiValidationException = (ApiValidationException) result.getBody();
        LOGGER.info(apiValidationException.getMessage()+":"+apiValidationException.getDetails());
    }  
    
    @Pointcut("execution(protected org.springframework.http.ResponseEntity fon.silab.FifaRangListServer.exceptions.RestExceptionHandler.handleDatabaseError(..))")
    private void databaseException() {
    }
    
    @AfterReturning(pointcut = "databaseException()", returning = "result")
    public void afterFatalDatabaseException(JoinPoint joinPoint, org.springframework.http.ResponseEntity result) {
        ApiException apiException = (ApiException) result.getBody();
        LOGGER.fatal(apiException.getMessage()+" Cannot create database connection.");
    }  
}

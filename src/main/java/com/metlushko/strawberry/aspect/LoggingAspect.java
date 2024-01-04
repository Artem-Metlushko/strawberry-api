package com.metlushko.strawberry.aspect;

import com.metlushko.strawberry.entity.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.metlushko.strawberry.service.UserEntityManagerService.findById(..)) ")
    public void logBeforeMethod(JoinPoint joinPoint) {
        logger.info("=|=|=|=|=|=|=|=> Before run method : {}", joinPoint.getSignature().getName());
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        logger.info("INPUT VALUE : {}", Arrays.stream(joinPoint.getArgs()).findFirst());
        logger.info("INPUT ParameterTypes : {}", (Object) signature.getParameterTypes());

    }

    @AfterReturning(pointcut = "execution(* com.metlushko.strawberry.service.UserEntityManagerService.findById(..))", returning = "user")
    public void logAfterMethod(JoinPoint joinPoint, User user) {

        logger.info("=|=|=|=|=|=|=|=> After run method: {}", joinPoint.getSignature().getName());
        logger.info("OUTPUT VALUE: {}", user);


    }

    @Before("execution ( public void findAll())")
    public void logBeforeCallAllMethod(JoinPoint joinPoint) {
        logger.info("Before run method : {}", joinPoint.getSignature().getName());
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        logger.info("INPUT Class : {}", signature.getClass());
    }
}



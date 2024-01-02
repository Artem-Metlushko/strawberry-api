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
    public  void logBeforeMethod(JoinPoint joinPoint){
        logger.info("=|=|=|=|=|=|=|=>           Before run method : "+joinPoint.getSignature().getName());
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        logger.info("INPUT VALUE :"+Arrays.stream(joinPoint.getArgs()).findFirst().orElseThrow().toString());
        logger.info("INPUT ParameterTypes :"+Arrays.toString(signature.getParameterTypes()));

    }

    @AfterReturning(pointcut = "execution(* com.metlushko.strawberry.service.UserEntityManagerService.findById(..))", returning = "result")
    public void logAfterMethod(JoinPoint joinPoint,Object result){
        if (result instanceof User) {
            User user = (User) result;
            logger.info("=|=|=|=|=|=|=|=> After run method: " + joinPoint.getSignature().getName());
            logger.info("OUTPUT VALUE: " + user);

        } else {
            logger.warn("Returned object is not of type User.");
        }


    }
}



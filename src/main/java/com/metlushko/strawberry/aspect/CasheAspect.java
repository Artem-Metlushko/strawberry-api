package com.metlushko.strawberry.aspect;

import com.metlushko.strawberry.entity.User;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Aspect
public class CasheAspect {

    private final Map<Long, User> casheMap;
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("@annotation(com.metlushko.strawberry.aspect.annotation.FindByIdCashe)")
    public void findByIdPoint() {

    }

    @Around(value = "findByIdPoint()")
    public Optional<User> findByIdAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("---------------------------");
        logMapIsEmpty();

        Long id = (Long) joinPoint.getArgs()[0];

        Optional<User> userFromCashe = Optional.ofNullable(casheMap.get(id));

        if (userFromCashe.isPresent()) {
            logToTerminal();
            return userFromCashe;
        }


        Optional<User> userFromDatabase = (Optional<User>) joinPoint.proceed();

        userFromDatabase.ifPresent(user -> casheMap.put(id, user));

        logToTerminal();
        logger.info("---------------------------");
        return userFromDatabase;

    }

    private void logToTerminal() {
        String mapToString = casheMap.entrySet().stream().toList().toString();

        logger.info("HASHMAP : {}", mapToString);

    }

    private void logMapIsEmpty() {
        if (casheMap.isEmpty()) {
            logger.info("HASHMAP :EMPTY");
        }
    }

    @Pointcut("@annotation(com.metlushko.strawberry.aspect.annotation.SaveCashe)")
    public void savePoint() {
    }

    @Around(value = "savePoint()")
    public User saveBeforeAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        logger.info("---------------------------");
        logMapIsEmpty();

        User userFromDatabase = (User) proceedingJoinPoint.proceed();

        casheMap.put(userFromDatabase.getId(), userFromDatabase);

        logToTerminal();
        logger.info("---------------------------");
        return userFromDatabase;
    }

    @Pointcut("@annotation(com.metlushko.strawberry.aspect.annotation.UpdateCashe)")
    public void updatePoint() {
    }

    @Around(value = "updatePoint()")
    public Object updateAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        logger.info("---------------------------");
        logMapIsEmpty();

        User user = (User) proceedingJoinPoint.getArgs()[0];
        Long userId = (Long) proceedingJoinPoint.getArgs()[1];


        proceedingJoinPoint.proceed();

        User userFromCashe = casheMap.put(userId, user);

        logToTerminal();
        logger.info("---------------------------");
        return userFromCashe;
    }

    @Pointcut("@annotation(com.metlushko.strawberry.aspect.annotation.DeleteCashe)")
    public void deletePoint() {
    }

    @Around(value = "deletePoint()")
    public Object deleteAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        logger.info("---------------------------");
        logMapIsEmpty();

        Long id = (Long) proceedingJoinPoint.getArgs()[0];
        Optional<User> cachedUser = Optional.ofNullable(casheMap.get(id));
        if (cachedUser.isPresent()) {
            casheMap.remove(id);
            logToTerminal();
            logger.info("---------------------------");
            return proceedingJoinPoint.proceed();
        }
        logToTerminal();
        logger.info("---------------------------");
        return  proceedingJoinPoint.proceed();
    }
}

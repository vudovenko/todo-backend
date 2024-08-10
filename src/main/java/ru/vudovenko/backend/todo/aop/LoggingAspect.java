package ru.vudovenko.backend.todo.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
public class LoggingAspect {

    @Around("execution(* ru.vudovenko.backend.todo.controllers..*(..)))")
    public Object profileControllerMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        log.debug("-------- Executing " + className + "." + methodName + "   ----------- ");

        Object result = proceedingJoinPoint.proceed();

        log.debug("-------- End of " + className + "." + methodName + "   ----------- ");

        return result;
    }
}

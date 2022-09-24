package ru.iporsyak.first_spring_gradle.aop;

import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log
public class LoggingAspect {
    // аспект будет выполнятся для всех методов из пакета controller
    @Around("execution(* ru.iporsyak.first_spring_gradle.controller..*(..)))")
    public Object profileControllerMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        // получить информацию о том какой класс и метод выполняется
        String className = methodSignature.getDeclaringTypeName();
        String methodName = methodSignature.getName();
        log.info("----------- Executing " + className + ". Method " + methodName + "----------");
        // выполняем сам метод
        return proceedingJoinPoint.proceed();
    }


}

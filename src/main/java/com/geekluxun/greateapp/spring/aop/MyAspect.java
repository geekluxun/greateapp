package com.geekluxun.greateapp.spring.aop;

import org.aspectj.apache.bcel.classfile.Method;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by luxun on 2017/11/7.
 */
@Aspect
@Component
public class MyAspect {
    private static final Logger logger = LoggerFactory.getLogger(MyAspect.class);

    /**
     * 在某个package内任意一方法(不包含子包)
     */
    @Pointcut("execution(* com.geekluxun.greateapp.service.*.*(..))")
    public void pointcut1(){
    }

    /**
     * 在某个package内任意一方法(包含子包!!!)
     */
    @Pointcut("execution(* com.geekluxun.greateapp.service..*.*(..))")
    public void pointcut2(){
    }

    /**
     * 在某一个package内(包含子包!!!)
     */
    @Pointcut("within(com.geekluxun.greateapp.controller..*)")
    public void pointcut3(){

    }

    /**
     * 在某一个package内(不包含子包)
     */
    @Pointcut("within(com.geekluxun.greateapp.controller.*)")
    public void pointcut4(){}

    /**
     * 注解
     */
    @Pointcut("@annotation(com.geekluxun.greateapp.annotation.MyAnnotation)")
    public void pointcut5(){}

    /**
     * 通过bean的id或者name
     */
    @Pointcut("bean(userService33)")
    public void pointcut6(){}

    /**
     * 实现某个接口
     */
    @Pointcut("this(com.geekluxun.greateapp.service.UserService.UserService)")
    public void pointcut7(){}

    /**
     * service任一方法 同时方法两个参数，参数类型分别是string ,map
     */
    @Pointcut("execution(* com.geekluxun.greateapp.service..*.*(..)) && args(java.lang.String, java.util.HashMap)")
    public void pointcut8(){}

    /**
     * 方法的入参是一个使用注解@MyAnnotation 的类型
     */
    @Pointcut("execution(* com.geekluxun.greateapp.service..*.*(..)) && @args(com.geekluxun.greateapp.annotation.MyAnnotation, com.geekluxun.greateapp.annotation.MyAnnotation)")
    public void pointcut9(){}


    @Before("pointcut9()")
    public void doBefore(JoinPoint joinPoint){
        logger.info(" ============ 服务调用前 =============");
        logger.info("调用:" + joinPoint.getSignature().toShortString());
        Object[] argcs = joinPoint.getArgs();
        for (int i = 0; i < argcs.length; i++){
            logger.info("调用方法参数:" + i + ": " + argcs[i].toString());
        }

    }

    @After("pointcut1()")
    public void doAfter(){
        logger.info(" ============ 服务调用后 =============");
    }

    @AfterReturning(pointcut = "pointcut1()" ,returning = "retval")
    public void doAfterReturn(Object retval){
        logger.info(" ========== 方法返回值为：============ {}", retval);
    }

    @AfterThrowing(pointcut = "pointcut1()", throwing = "ex")
    public void doAfterThrow(Exception ex){
        logger.error(" ========== 调用发生异常： ========== ", ex);
    }

    @Around("pointcut1()")
    public void doAfterFinally(ProceedingJoinPoint point) throws Exception{
        logger.error(" ========== 环绕通知 ========== ");
        try {
            point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}

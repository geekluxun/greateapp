package com.geekluxun.greateapp.spring.aop;

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

    @Pointcut("execution(* com.geekluxun.greateapp.service.*.*(..))")
    public void pointcut1(){

    }

    @Before("pointcut1()")
    public void doBefore(){
        logger.info(" ============ 服务调用前 =============");

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

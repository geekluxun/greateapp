package com.geekluxun.greateapp.spring.aop;

import com.geekluxun.greateapp.controller.MainController;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Project: greateapp
 * Author: luxun
 * Date: 2018/1/31 11:27
 * Description: web请求和响应的相关切面
 */
@Aspect
@Service
public class WebAspectService {

    private static final Logger logger = LoggerFactory.getLogger(WebAspectService.class);

    /**
     * controller包及其子包中 且用@RequestMapping注解的方法
     */
    @Pointcut("within(com.geekluxun.greateapp.controller..*) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    private void controllerPointcut() {

    }


    /**
     * 必须捕获异常，否则会导致原有方法无法执行！！！
     *
     * @param joinPoint
     */
    @Before("controllerPointcut()")
    private void requestBeforeHandler(JoinPoint joinPoint) {

        try {

            RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes sra = (ServletRequestAttributes) ra;
            HttpServletRequest request = sra.getRequest();

            String url = request.getRequestURI();
            String method = request.getMethod();
            String contentType = request.getContentType();

            /**
             * 不能通过这种方式读取body,因为流已经关闭 注解@RequestBody导致已经关闭了流
             */
            //       StringBuilder body = new StringBuilder();
            //        BufferedReader reader = null;
            //        try {
            //            reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            //            String c;
            //            while ((c = reader.readLine()) != null) {
            //                body.append(c);
            //            }
            //        } catch (IOException e) {
            //            e.printStackTrace();
            //        } finally {
            //            try {
            //                reader.close();
            //            } catch (IOException e) {
            //                e.printStackTrace();
            //            }
            //        }

            logger.info("WEB请求URL:" + url + " Method:" + method + " ContentType:" + contentType);

            Object[] argcs = joinPoint.getArgs();
            for (int i = 0; i < argcs.length; i++) {
                logger.info("argc[" + i + "]: " + argcs[i].toString());
            }

            throw new RuntimeException("模拟前置增强异常！");
        } catch (Exception e) {
            logger.error("web请求前置增强:", e);
        }
    }
}

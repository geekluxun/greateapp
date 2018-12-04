package com.geekluxun.greateapp.spring.springmvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: luxun
 * @Create: 2018-11-28 22:03
 * @Description: @ExceptionHandler, @InitBinder, and @ModelAttribute这些注解
 * 的方法能跨越多个控制器使用
 * @Other:
 */
@ControllerAdvice(annotations = Controller.class)
@Slf4j
public class ControllerAdviceExample {
    /**
     * 通用异常处理器
     *
     * @param e
     * @return
     */
    @ExceptionHandler
    public ResponseEntity<String> handle(Exception e, HttpServletRequest request) {
        log.error("请求url:" + request.getRequestURL() + "发生了异常:", e);
        ResponseEntity responseEntity = new ResponseEntity("我是通用的异常处理：抱歉，系统发生了异常", HttpStatus.OK);
        return responseEntity;
    }
}

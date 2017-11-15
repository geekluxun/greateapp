package com.geekluxun.greateapp.spring.aop;

import com.geekluxun.greateapp.execption.ParaValidException;
import com.geekluxun.greateapp.execption.ParamValidException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by luxun on 2017/11/7.
 */
@Component
@Aspect
public class ParamValidAspect {

    Logger log = LoggerFactory.getLogger(getClass());

    //@Pointcut("execution(* com.geekluxun.greateapp.service..*.*(..)) && @args(com.geekluxun.greateapp.annotation.ParaValidator)")
    @Pointcut("execution(* com.geekluxun.greateapp.service..*.*(..))")
    public void controllerBefore() {
    }


    ParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();


    @Before("controllerBefore()")
    public void before(JoinPoint point) throws Exception,NoSuchMethodException, SecurityException, ParamValidException {
        // 获得切入目标对象
        Object target = point.getThis();
        // 获得切入方法参数
        Object[] args = point.getArgs();
        // 获得切入的方法
        Method method = ((MethodSignature) point.getSignature()).getMethod();

        // 执行校验，获得校验结果
        Set<ConstraintViolation<Object>> validResult = validMethodParams(target, method, args);

        if (!validResult.isEmpty()) {
//            String[] parameterNames = parameterNameDiscoverer.getParameterNames(method); // 获得方法的参数名称
//            List<FieldError> errors = validResult.stream().map(constraintViolation -> {
//                PathImpl pathImpl = (PathImpl) constraintViolation.getPropertyPath();  // 获得校验的参数路径信息
//                int paramIndex = pathImpl.getLeafNode().getParameterIndex(); // 获得校验的参数位置
//                String paramName = parameterNames[paramIndex];  // 获得校验的参数名称
//                FieldError error = new FieldError();  // 将需要的信息包装成简单的对象，方便后面处理
//                error.setName(paramName);  // 参数名称（校验错误的参数名称）
//                error.setMessage(constraintViolation.getMessage()); // 校验的错误信息
//                return error;
//            }).collect(Collectors.toList());
            String error = validResult.iterator().next().getMessage();

//            Iterator<Path.Node> propertyPath = validResult.iterator()
//                    .next()
//                    .getPropertyPath()
//                    .iterator();
//
//            Path.MethodNode methodNode = propertyPath.next().as( Path.MethodNode.class );
//
//            Path.ParameterNode parameterNode = propertyPath.next().as( Path.ParameterNode.class );


            throw new ParaValidException(error);  // 抛出异常，交给上层处理

        }
    }

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final ExecutableValidator validator = factory.getValidator().forExecutables();

    private <T> Set<ConstraintViolation<T>> validMethodParams(T obj, Method method, Object[] params) {
        return validator.validateParameters(obj, method, params);
    }
}
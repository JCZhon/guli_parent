package com.zjh.eduService.aop;

import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 使用aop实现日志记录
 */
@Aspect//定义切面类
@Component
@Slf4j
public class MyLogger {
    //private Logger logger = LoggerFactory.getLogger(MyLogger.class);
    //在不使用@Slf4j注解时使用


    @Pointcut(value = "execution( * com.zjh.eduService.controller.*.*(..))")  //定义切点PointCut
    public void myPointCut() {
    }

    //定义通知Advice
    @Around("myPointCut()")
    public Object myAdvice(ProceedingJoinPoint pjp) throws Throwable {
        String className = pjp.getTarget().getClass().toString();
        String methodName = pjp.getSignature().getName();   //获取方法名
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = sdf.format(new Date());
        Object obj = null;

        try {
            log.info("调用前：" + className + "." + methodName + "\n" +
                    " 传入参数为：" + Arrays.asList(pjp.getArgs())
                    + " " + nowTime);
            obj = pjp.proceed();
            log.info("调用后：" + className + "." + methodName + "\n" +
                    " 返回值为：" + Arrays.asList(obj)
                    + " " + nowTime);
        } catch (Throwable e) {
            log.info("The method" + methodName + "occurs excution:" + e);
            throw new RuntimeException(e);
        }
        return obj;
    }

    /**
     * 方法出现异常时执行代码
     */
    @AfterThrowing(value = "myPointCut()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        String methodName = joinPoint.getSignature().getName();
        log.info("The method" + methodName + " occurs excution:" + ex);
    }


    /**
     * AOP和自定义注解结合使用
     * 注解：MyLogging
     */
    //通过自定义注解来精确到某个需要aop加强的方法（切点找到使用了自定义注解的方法）
    @Pointcut("@annotation(com.zjh.eduService.myAnnotations.MyLogging)")
    public void myPcForAnno() {

    }

    //测试通过自定义注解精确找到目标方法
    @Before("@annotation(com.zjh.eduService.myAnnotations.MyLogging)")
    public void myAdviceTest() {
        System.out.println("方法执行前调用----------------------------------");
    }


}

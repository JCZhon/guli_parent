package com.zjh.servicebase.exception;


import com.zjh.commonUtils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理器
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 全局异常处理
     *
     * @param e
     * @return
     */
    //指定出现什么异常时执行这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody   //为了返回数据
    public R error(Exception e) {
        e.printStackTrace();
        return R.error().message("执行了全局异常处理..");
    }

    /**
     * 特定异常处理
     */
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody   //为了返回数据
    public R error(ArithmeticException e) {
        e.printStackTrace();
        return R.error().message("执行了ArithmeticException异常处理..");
    }

    /**
     * 自定义异常处理
     */
    @ExceptionHandler(CustomizeExceptiion.class)
    @ResponseBody   //为了返回数据
    public R error(CustomizeExceptiion e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMessage());
    }
}

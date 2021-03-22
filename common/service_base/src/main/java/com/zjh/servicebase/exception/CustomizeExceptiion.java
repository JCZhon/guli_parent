package com.zjh.servicebase.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 自定义异常类
 */
@Data
@AllArgsConstructor//生成有参数的构造方法
@NoArgsConstructor//生成无参数的构造方法
public class CustomizeExceptiion extends RuntimeException {

    private Integer code;//状态码

    private String message;//异常信息

}

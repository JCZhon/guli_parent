package com.zjh.commonUtils;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一返回结果
 * 链式编程（以后格式是一样的）
 */
@Data
public class R {

    private static final Integer SUCCESS = 20000;//成功
    private static final Integer ERROR = 20001;//失败

    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty("返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<>();

    //把构造方法私有化
    private R() {
    }

    //成功
    public static R ok() {
        R r = new R();
        r.setSuccess(true);
        r.setCode(SUCCESS);
        r.setMessage("成功");
        return r;
    }

    //失败
    public static R error() {
        R r = new R();
        r.setSuccess(false);
        r.setCode(ERROR);
        r.setMessage("失败");
        return r;
    }

    public R success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public R code(Integer code) {
        this.setCode(code);
        return this;
    }

    public R message(String message) {
        this.setMessage(message);
        return this;
    }

    public R data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }

    //根据boolean条件判断选用ok/error
    public static R judgeByBo(boolean condition) {
        if (condition) {
            return R.ok();
        } else {
            return R.error();
        }
    }
}

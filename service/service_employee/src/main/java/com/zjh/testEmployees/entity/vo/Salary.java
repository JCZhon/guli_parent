package com.zjh.testEmployees.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Salary {
    @ApiModelProperty("员工薪资")
    private Double salary;

    @ApiModelProperty("薪资等级")
    private String rank;
}

package com.zjh.testEmployees.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author zjh
 * @since 2021-01-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Employees对象", description = "")
public class Employees implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "员工编号")
    @TableId(value = "employee_id", type = IdType.AUTO)
    private Integer employeeId;

    @ApiModelProperty(value = "名")
    private String firstName;

    @ApiModelProperty(value = "姓")
    private String lastName;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "电话号码")
    private String phoneNumber;

    @ApiModelProperty(value = "工种编号")
    private String jobId;

    @ApiModelProperty(value = "月薪")
    private Double salary;

    @ApiModelProperty(value = "奖金率")
    private Double commissionPct;

    @ApiModelProperty(value = "上级领导员工编号")
    private Integer managerId;

    @ApiModelProperty(value = "部门编号")
    private Integer departmentId;

    @ApiModelProperty(value = "入职日期")
    private Date hiredate;


}

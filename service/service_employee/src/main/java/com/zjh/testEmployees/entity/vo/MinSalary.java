package com.zjh.testEmployees.entity.vo;

import lombok.Data;

/**
 * 工资最少的员工信息
 */
@Data
public class MinSalary {
    private String lastName;

    private String jobId;

    private String salary;
}

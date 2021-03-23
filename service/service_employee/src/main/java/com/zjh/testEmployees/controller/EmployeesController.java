package com.zjh.testEmployees.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.TypeDefParticle;
import com.zjh.commonUtils.R;
import com.zjh.testEmployees.entity.vo.*;
import com.zjh.testEmployees.service.EmployeesService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * employees模块前端控制器
 * </p>
 *
 * @author zjh
 * @since 2021-01-21
 */
@RestController
@RequestMapping("/testEmployees/employees")
public class EmployeesController {
    @Autowired
    private EmployeesService employeesService;

    /**
     * 获取员工薪资并进行分级
     * rank等级 A,B,C,D
     */
    @GetMapping("getSalary")
    @ApiOperation("获得员工薪资并分级")
    public R getSalary() {
        List<Salary> list = employeesService.getSalary();
        return R.ok().data("data", list);
    }

    /**
     * 获取总条数
     * 用于测试count函数
     *
     * @return
     */
    @GetMapping("getCount")
    @ApiOperation("获取总条数")
    public String getCount() {
        return employeesService.getCount().toString();
    }

    /**
     * 获取员工名、部门编号和部门名
     */
    @GetMapping("getEmployee")
    @ApiOperation("获取员工信息")
    public R getEmployee() {
        List<Employee> list = employeesService.getEmployee();
        return R.ok().data("data", list);
    }

    /**
     * 查询有奖金或者无奖金的员工信息
     * 员工信息包括：员工名、部门名、地址id、所在城市
     * condition:有奖金/无奖金
     */
    @GetMapping("getEmpployeeByBonus/{condition}")
    @ApiOperation("根据是否有奖金查询员工信息")
    public R getByBonus(String condition) {
        List<EmployeeByBonus> list = employeesService.getByBonus(condition);
        return R.ok().data("data", list);
    }

    /**
     * 获取部门信息
     * 部门名称、部门id、部门员工总数
     */
    @GetMapping("getDepartment")
    @ApiOperation("获取部门信息")
    public R getDepartment() {
        List<DepartmentVo> list = employeesService.getDepartment();
        return R.ok().data("data", list);
    }

    /**
     * 根据输入的员工名查询该员工所在部门的员工名及薪资
     */
    @GetMapping("getSame/{employeeName}")
    @ApiOperation("查询员工同部门的员工信息")
    public R getSame(@PathVariable @NotNull String employeeName) {
        List<SameEmployee> list = employeesService.getSameEmployee(employeeName);
        return R.ok().data("data", list);
    }

    @GetMapping("getMin")
    @ApiOperation("查询工资最少的员工信息")
    public R getMin() {
        List<MinSalary> list = employeesService.getMinSalary();

        return R.ok().data("data", list);
    }

}


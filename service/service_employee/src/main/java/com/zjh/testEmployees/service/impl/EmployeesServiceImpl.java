package com.zjh.testEmployees.service.impl;

import com.zjh.servicebase.exception.CustomizeExceptiion;
import com.zjh.testEmployees.entity.Employees;
import com.zjh.testEmployees.entity.vo.*;
import com.zjh.testEmployees.mapper.EmployeesMapper;
import com.zjh.testEmployees.service.EmployeesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zjh
 * @since 2021-01-21
 */
@Service
public class EmployeesServiceImpl extends ServiceImpl<EmployeesMapper, Employees> implements EmployeesService {

    @Autowired
    private EmployeesMapper employeesMapper;


    @Override
    public List<Salary> getSalary() {
        List<Salary> list = employeesMapper.getSalary();
        return list;
    }

    @Override
    public Integer getCount() {
        return employeesMapper.getCount();
    }

    @Override
    public List<Employee> getEmployee() {
        List<Employee> list = employeesMapper.getEmployee();
        return list;
    }

    @Override
    public List<EmployeeByBonus> getByBonus(String condition) {
        List<EmployeeByBonus> list = new ArrayList<>();
        if (condition == null) {
            throw new CustomizeExceptiion(20001, "判断条件为空");
        }
        if (condition.equals("有奖金")) {
            list = employeesMapper.getEmployeeByTrue();
        } else if (condition.equals("无奖金")) {
            list = employeesMapper.getEmployeeByFalse();
        }
        return list;
    }

    @Override
    public List<DepartmentVo> getDepartment() {
        return employeesMapper.getDepartment();

    }

    @Override
    public List<SameEmployee> getSameEmployee(String employeeName) {
        return employeesMapper.getSameEmployee(employeeName);
    }

    @Override
    public List<MinSalary> getMinSalary() {
        return employeesMapper.getMinSalary();
    }


}

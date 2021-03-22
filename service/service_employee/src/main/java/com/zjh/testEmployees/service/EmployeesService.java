package com.zjh.testEmployees.service;

import com.zjh.testEmployees.entity.Employees;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjh.testEmployees.entity.vo.*;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zjh
 * @since 2021-01-21
 */
public interface EmployeesService extends IService<Employees> {

    List<Salary> getSalary();

    Integer getCount();

    List<Employee> getEmployee();

    List<EmployeeByBonus> getByBonus(String condition);

    List<DepartmentVo> getDepartment();

    List<SameEmployee> getSameEmployee(String employeeName);

}

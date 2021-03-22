package com.zjh.testEmployees.mapper;

import com.zjh.testEmployees.entity.Employees;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjh.testEmployees.entity.vo.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zjh
 * @since 2021-01-21
 */
@Repository
public interface EmployeesMapper extends BaseMapper<Employees> {

    List<Salary> getSalary();

    Integer getCount();

    List<Employee> getEmployee();

    List<EmployeeByBonus> getEmployeeByTrue();

    List<EmployeeByBonus> getEmployeeByFalse();

    List<DepartmentVo> getDepartment();

    List<SameEmployee> getSameEmployee(String employeeName);

    List<Integer> salarybetween();
}

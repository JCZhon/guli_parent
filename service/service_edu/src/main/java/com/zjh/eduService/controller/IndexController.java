package com.zjh.eduService.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjh.commonUtils.R;
import com.zjh.eduService.entity.EduCourse;
import com.zjh.eduService.entity.EduTeacher;
import com.zjh.eduService.service.EduCourseService;
import com.zjh.eduService.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 查询课程名师接口
 */

@RestController
@RequestMapping("eduService/index")
@CrossOrigin //解决跨域
@Api("查询课程名师接口")
public class IndexController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @Autowired
    private EduCourseService eduCourseService;

    /**
     * 查询最新前4条讲师数据
     * 查询最新前8条课程数据
     */

    @GetMapping("index")
    @ApiOperation("查询课程名师")
    public R index() {
        //查询前4条讲师数据
        QueryWrapper<EduTeacher> wrapperTeacher = new QueryWrapper<>();
        wrapperTeacher.orderByDesc("id");
        wrapperTeacher.last("limit 4");
        List<EduTeacher> teacherList = eduTeacherService.list(wrapperTeacher);

        //查询前8条热门课程
        QueryWrapper<EduCourse> wrapperCourse = new QueryWrapper<>();
        wrapperTeacher.orderByDesc("id");
        wrapperTeacher.last("limit 8");
        List<EduCourse> courseList = eduCourseService.list(wrapperCourse);

        return R.ok().data("teacherList", teacherList).data("courseList", courseList);
    }


}

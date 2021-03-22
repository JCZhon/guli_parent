package com.zjh.eduService.controller;


import com.zjh.commonUtils.R;
import com.zjh.eduService.entity.EduCourse;
import com.zjh.eduService.entity.constant.EduConstant;
import com.zjh.eduService.entity.vo.CourseInfoVo;
import com.zjh.eduService.entity.vo.CoursePublishVo;
import com.zjh.eduService.myAnnotations.MyLogging;
import com.zjh.eduService.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程管理 前端控制器
 * </p>
 *
 * @author zjh
 * @since 2020-12-23
 */
@RestController
@RequestMapping("/eduService/edu-course")
@CrossOrigin
@Api(value = "课程信息管理")
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    /**
     * 添加课程基本信息
     */
    @PostMapping("addCourseInfo")
    @ApiOperation("添加课程基本信息")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        eduCourseService.saveCourseInfo(courseInfoVo);
        return R.ok();
    }

    /**
     * 根据课程id查询课程信息
     */
    @GetMapping("getCourseInfo/{courseID}")
    @ApiOperation("根据课程id查询课程信息")
    @MyLogging
    public R getCourseInfo(@ApiParam(name = "courseID", value = "课程id", required = true)
                           @PathVariable String courseID) {
        CourseInfoVo courseInfoVo = eduCourseService.getCourseInfo(courseID);
        return R.ok().data("courseInfo", courseInfoVo);
    }

    /**
     * 修改课程信息
     */
    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        eduCourseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }

    /**
     * 根据课程id查询课程确认信息
     */
    @GetMapping("getPublishCourseInfo/{id}")
    @ApiOperation("课程信息确认")
    public R getPublishCourseInfo(@ApiParam(name = "id", value = "课程id", required = true)
                                  @PathVariable String id) {
        CoursePublishVo coursePublishVo = eduCourseService.getPublishCourse(id);
        return R.ok().data("coursePublishInfo", coursePublishVo);
    }

    /**
     * 修改课程状态
     * 将默认的Draft改成Normal
     */
    @GetMapping("changeStatus/{id}")
    public R changeStatus(@PathVariable String id) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus(EduConstant.STATUS_NORMAL);
        eduCourseService.updateById(eduCourse);
        return R.ok();
    }

    /**
     * 根据名称和状态动态分页查询课程
     */
    @PostMapping("findByCondition")
    @ApiOperation("根据名称和状态动态分页查询课程")
    public R findByCondition(@RequestBody EduCourse eduCourse) {
        return eduCourseService.findByCondition(eduCourse);
    }

    /**
     * 删除课程
     * 小节、章节、描述全部删除
     */
    @GetMapping("deleteCourse/{courseId}")
    @ApiOperation("删除课程")
    public R deleteCourse(@ApiParam(name = "courseId", value = "课程id", required = true)
                          @PathVariable String courseId) {
        eduCourseService.removeCourse(courseId);
        return R.ok();
    }

    @GetMapping("test")
    @ApiOperation("简单测试")
    public String test() {
        return "钟家华";
    }
}


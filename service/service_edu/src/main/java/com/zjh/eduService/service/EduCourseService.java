package com.zjh.eduService.service;

import com.zjh.commonUtils.R;
import com.zjh.eduService.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjh.eduService.entity.vo.CourseInfoVo;
import com.zjh.eduService.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author zjh
 * @since 2020-12-23
 */
public interface EduCourseService extends IService<EduCourse> {

    //添加课程基本信息的方法
    void saveCourseInfo(CourseInfoVo courseInfoVo);

    //根据课程id查询课程信息
    CourseInfoVo getCourseInfo(String courseID);

    //修改课程信息
    void updateCourseInfo(CourseInfoVo courseInfoVo);

    //查询课程确认信息
    CoursePublishVo getPublishCourse(String id);

    //根据名称和状态分页查询课程
    R findByCondition(EduCourse eduCourse);

    //删除课程
    void removeCourse(String courseId);
}

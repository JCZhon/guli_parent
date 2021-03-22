package com.zjh.eduService.mapper;

import com.zjh.eduService.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjh.eduService.entity.vo.CoursePublishVo;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author zjh
 * @since 2020-12-23
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    public CoursePublishVo getPublishCourseInfo(String courseId);

}

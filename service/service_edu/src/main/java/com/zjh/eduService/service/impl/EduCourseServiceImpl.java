package com.zjh.eduService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjh.commonUtils.R;
import com.zjh.eduService.entity.EduChapter;
import com.zjh.eduService.entity.EduCourse;
import com.zjh.eduService.entity.EduCourseDescription;
import com.zjh.eduService.entity.EduVideo;
import com.zjh.eduService.entity.vo.CourseInfoVo;
import com.zjh.eduService.entity.vo.CoursePublishVo;
import com.zjh.eduService.mapper.EduCourseMapper;
import com.zjh.eduService.service.EduChapterService;
import com.zjh.eduService.service.EduCourseDescriptionService;
import com.zjh.eduService.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjh.eduService.service.EduVideoService;
import com.zjh.servicebase.exception.CustomizeExceptiion;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 课程管理 服务实现类
 * </p>
 *
 * @author zjh
 * @since 2020-12-23
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Autowired
    private EduChapterService eduChapterService;

    @Autowired
    private EduVideoService eduVideoService;


    /**
     * 添加课程基本信息
     *
     * @param courseInfoVo
     */
    @Override
    public void saveCourseInfo(CourseInfoVo courseInfoVo) {
        //向课程表添加课程基本信息
        //把信息转换过来
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        boolean flag = this.save(eduCourse);
        if (!flag) {
            throw new CustomizeExceptiion(20001, "添加课程信息失败");
        }
        String cid = eduCourse.getId();
        //向课程简介表添加课程简介信息
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        //设置描述id就是课程id，建立一对一关系
        eduCourseDescription.setId(cid);
        eduCourseDescriptionService.save(eduCourseDescription);

    }

    /**
     * 根据课程id查询课程信息
     *
     * @param courseID
     * @return
     */
    @Override
    public CourseInfoVo getCourseInfo(String courseID) {
        //查询课程表
        EduCourse eduCourse = baseMapper.selectById(courseID);
        //查询描述表
        EduCourseDescription eduCourseDescription = eduCourseDescriptionService.getById(courseID);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse, courseInfoVo);
        courseInfoVo.setDescription(eduCourseDescription.getDescription());
        return courseInfoVo;
    }

    /**
     * 修改课程信息
     *
     * @param courseInfoVo
     */
    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        //修改课程表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int update = baseMapper.insert(eduCourse);
        if (update == 0) {
            throw new CustomizeExceptiion(20001, "课程表更新失败");
        }
        //修改课程描述表
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(courseInfoVo.getId());
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        boolean save = eduCourseDescriptionService.save(eduCourseDescription);
        if (!save) {
            throw new CustomizeExceptiion(20001, "课程描述表更新失败");
        }

    }

    /**
     * 查询课程确认信息
     *
     * @param id
     * @return
     */
    @Override
    public CoursePublishVo getPublishCourse(String id) {
        CoursePublishVo coursePublishVo = baseMapper.getPublishCourseInfo(id);
        return coursePublishVo;
    }

    /**
     * 根据名称和状态分页查询课程
     *
     * @param eduCourse
     * @return
     */
    @Override
    public R findByCondition(EduCourse eduCourse) {
        Page<EduCourse> page = new Page<>();
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        String title = eduCourse.getTitle();
        String status = eduCourse.getStatus();
        if (!StringUtils.isEmpty(title)) {
            wrapper.like("title", title);
        }
        if (!StringUtils.isEmpty(status)) {
            wrapper.eq("status", status);
        }
        this.page(page, wrapper);//查询在这里进行，并将数据封装
        long total = page.getTotal();
        List<EduCourse> records = page.getRecords();
        return R.ok().data("total", total).data("records", records);
    }

    /**
     * 删除课程
     *
     * @param courseId
     */
    @Override
    public void removeCourse(String courseId) {
        //根据课程id删除课程小节
        eduVideoService.removeByCourseId(courseId);

        //根据课程id删除课程章节
        eduChapterService.removeByCourseId(courseId);

        //根据课程id删除课程描述
        eduCourseDescriptionService.removeById(courseId);

        //根据课程id删除课程本身
        int result = baseMapper.deleteById(courseId);
        if (result == 0) {
            throw new CustomizeExceptiion(20001, "删除失败");
        }
    }


}

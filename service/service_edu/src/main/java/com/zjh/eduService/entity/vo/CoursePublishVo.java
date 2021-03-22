package com.zjh.eduService.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 课程发布信息
 */
@Data
@ApiModel("课程发布信息")
public class CoursePublishVo {


    private String id;

    private String title;

    private String cover;

    private Integer lessonNum;

    private String description;

    private String subjectLevelOne;

    private String subjectLevelTwo;

    private String teacherName;

    private String price;//只用于显示
}

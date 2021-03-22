package com.zjh.eduService.entity.chapter;

import com.alibaba.excel.annotation.ExcelProperty;
import com.zjh.commonUtils.LocalDateConcerter;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 课程章节,章节里面封装小节
 */
@Data
public class ChapterVo {

    //课程id
    @ExcelProperty(value ="课程id")
    private String id;

    //课程章节
    @ExcelProperty(value = "课程章节")
    private String title;

    //课程小节
    @ExcelProperty(value = "课程小节")
    List<VideoVo> children = new ArrayList<>();

}

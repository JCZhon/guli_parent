package com.zjh.eduService.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 上传课程分类信息
 * edu_subject两级分类对应的实体类(对应excel数据)
 */
@Data
public class SubjectData {

    @ExcelProperty(index = 0)//对应表格第一列
    private String oneSubjectName;

    @ExcelProperty(index = 1)//对应表格第二列
    private String twoSubjectName;
}

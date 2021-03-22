package com.zjh.eduService.controller;


import com.zjh.commonUtils.R;
import com.zjh.eduService.entity.EduSubject;
import com.zjh.eduService.entity.subject.OneSubject;
import com.zjh.eduService.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程分类
 * </p>
 *
 * @author zjh
 * @since 2020-12-22
 */
@RestController
@RequestMapping("/eduService/edu-subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    /**
     * 上传excel文件，将数据存入数据库
     *
     * @param file
     * @return
     */
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file) {
        eduSubjectService.addSubject(file, eduSubjectService);
        return R.ok();
    }

    /**
     * 课程分类列表（树形）
     */
    @GetMapping("getAllSubject")
    public R getAllSubject() {
        List<OneSubject> list = eduSubjectService.getAllSubject();
        return R.ok().data("list", list);
    }

}


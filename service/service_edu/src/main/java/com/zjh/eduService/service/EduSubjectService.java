package com.zjh.eduService.service;

import com.zjh.eduService.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjh.eduService.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import javax.security.auth.Subject;
import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author zjh
 * @since 2020-12-22
 */
public interface EduSubjectService extends IService<EduSubject> {

    /**
     * 上传excel文件将数据存入数据库
     *
     * @param file
     * @param eduSubjectService
     */
    void addSubject(MultipartFile file, EduSubjectService eduSubjectService);


    /**
     * 课程分类列表
     *
     * @return
     */
    List<OneSubject> getAllSubject();

}

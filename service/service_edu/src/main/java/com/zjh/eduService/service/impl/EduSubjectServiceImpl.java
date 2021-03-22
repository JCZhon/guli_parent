package com.zjh.eduService.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjh.eduService.entity.EduSubject;
import com.zjh.eduService.entity.excel.SubjectData;
import com.zjh.eduService.entity.subject.OneSubject;
import com.zjh.eduService.entity.subject.TwoSubject;
import com.zjh.eduService.listener.SubjectExcelListener;
import com.zjh.eduService.mapper.EduSubjectMapper;
import com.zjh.eduService.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * 课程分类
 * </p>
 *
 * @author zjh
 * @since 2020-12-22
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Autowired
    private EduSubjectMapper eduSubjectMapper;

    /**
     * 上传excel文件，将数据存到数据库
     *
     * @param file
     * @param eduSubjectService
     */
    @Override
    public void addSubject(MultipartFile file, EduSubjectService eduSubjectService) {
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, SubjectData.class, new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * 获取课程列表（自写）
     *
     * @return
     */
    @Override
    public List<OneSubject> getAllSubject() {
        List<OneSubject> finalSubjectsList = new ArrayList<>();
        //先全部拿到一级和二级分类发数据
        QueryWrapper<EduSubject> wrapper01 = new QueryWrapper();
        wrapper01.eq("parent_id", 0);
        List<EduSubject> oneSubjectList = this.list(wrapper01);

        QueryWrapper<EduSubject> wrapper02 = new QueryWrapper<>();
        wrapper02.ne("parent_id", 0);
        List<EduSubject> twoSubjectList = this.list(wrapper02);

        //将拿到的数据封装到OneSubject-->一级分类实体
        //先分别把两个分级的数据拆出来
        List<OneSubject> oneSubjects = new ArrayList<>();
        for (EduSubject one :
                oneSubjectList) {
            OneSubject oneSubject = new OneSubject();
            BeanUtils.copyProperties(one, oneSubject);
            oneSubjects.add(oneSubject);

            List<TwoSubject> twoSubjects = new ArrayList<>();
            for (EduSubject two :
                    twoSubjectList) {
                TwoSubject twoSubject = new TwoSubject();
                if (two.getParentId().equals(one.getId())) {
                    BeanUtils.copyProperties(two, twoSubject);
                    twoSubjects.add(twoSubject);
                }
                oneSubject.setChildren(twoSubjects);
            }
        }
        return finalSubjectsList;
    }


}

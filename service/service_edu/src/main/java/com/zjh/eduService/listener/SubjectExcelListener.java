package com.zjh.eduService.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjh.eduService.entity.EduSubject;
import com.zjh.eduService.entity.excel.SubjectData;
import com.zjh.eduService.service.EduSubjectService;
import com.zjh.servicebase.exception.CustomizeExceptiion;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    public EduSubjectService eduSubjectService;

    public SubjectExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null) {
            throw new CustomizeExceptiion(20001, "文件数据为空");
        }

        EduSubject existOneSubject = this.existOneSubject(eduSubjectService, subjectData.getOneSubjectName());
        if (existOneSubject == null) {
            existOneSubject = new EduSubject();
            existOneSubject.setTitle(subjectData.getOneSubjectName());
            existOneSubject.setParentId("0");
            eduSubjectService.save(existOneSubject);
        }

        String pid = existOneSubject.getId();   //这是已经解析了的数据（一级类别），已经自动生成了id
        EduSubject existTwoSubject = this.existTwoSubject(eduSubjectService, subjectData.getTwoSubjectName(), pid);
        if (existTwoSubject == null) {
            existTwoSubject = new EduSubject();
            existTwoSubject.setTitle(subjectData.getTwoSubjectName());
            existTwoSubject.setParentId(pid);
            eduSubjectService.save(existTwoSubject);
        }
    }

    //获得excel中和数据库中相同的数据
    //因为监听器是一行一行解析，解析一行包括一级类别和二级类别两个数据
    private EduSubject existOneSubject(EduSubjectService eduSubjectService, String name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", 0);
        EduSubject oneSubject = eduSubjectService.getOne(wrapper);
        return oneSubject;
    }

    private EduSubject existTwoSubject(EduSubjectService eduSubjectService, String name, String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", pid);
        EduSubject twoSubject = eduSubjectService.getOne(wrapper);
        return twoSubject;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}

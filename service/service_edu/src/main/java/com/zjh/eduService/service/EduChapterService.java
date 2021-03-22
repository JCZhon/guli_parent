package com.zjh.eduService.service;

import com.zjh.eduService.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjh.eduService.entity.chapter.ChapterVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author zjh
 * @since 2020-12-23
 */
public interface EduChapterService extends IService<EduChapter> {

    //添加课程章节小节
    List<ChapterVo> addChapterVideo(String courseID);

    //根据小节id删除章节
    boolean deleteChapter(String chapterID);

    //根据课程id删除章节
    void removeByCourseId(String courseId);

}

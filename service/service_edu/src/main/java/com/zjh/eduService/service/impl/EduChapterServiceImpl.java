package com.zjh.eduService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjh.eduService.entity.EduChapter;
import com.zjh.eduService.entity.EduVideo;
import com.zjh.eduService.entity.chapter.ChapterVo;
import com.zjh.eduService.entity.chapter.VideoVo;
import com.zjh.eduService.mapper.EduChapterMapper;
import com.zjh.eduService.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjh.eduService.service.EduVideoService;
import com.zjh.servicebase.exception.CustomizeExceptiion;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程大纲列表 服务实现类
 * </p>
 *
 * @author zjh
 * @since 2020-12-23
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService eduVideoService;

    /**
     * 课程大纲列表（查询）
     *
     * @param courseID
     * @return
     */
    @Override
    public List<ChapterVo> addChapterVideo(String courseID) {
        List<ChapterVo> finalNeedlist = new ArrayList<>();

        //根据课程id查询课程里面的所有章节
        QueryWrapper<EduChapter> chapterWrapper = new QueryWrapper<>();
        chapterWrapper.eq("course_id", courseID);
        List<EduChapter> eduChapterList = baseMapper.selectList(chapterWrapper);

        //根据课程id查询课程里面的所有小节
        QueryWrapper<EduVideo> videoWrapper = new QueryWrapper<>();
        videoWrapper.eq("course_id", courseID);
        List<EduVideo> eduVideoList = eduVideoService.list(videoWrapper);

        //遍历章节list集合进行封装
        for (EduChapter eduChapter : eduChapterList
        ) {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter, chapterVo);
            finalNeedlist.add(chapterVo);

            //遍历小节list集合进行封装
            List<VideoVo> videoVoList = new ArrayList<>();
            for (EduVideo eduVideo :
                    eduVideoList) {
                if (eduVideo.getChapterId().equals(eduChapter.getId())) {
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo, videoVo);
                    videoVoList.add(videoVo);
                }
            }
            chapterVo.setChildren(videoVoList);
        }
        return finalNeedlist;
    }

    /**
     * 删除章节
     * 思路：必须删除小节才能删除章节
     *
     * @param chapterID
     */
    @Override
    public boolean deleteChapter(String chapterID) {

        //根据chapterID查询小节表，如果有数据，则不进行删除
        //这里只需要判断是否有小节，不用得到数据，所以只需要一个count总数
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id", chapterID);
        int count = eduVideoService.count(wrapper);
        if (count > 0) {//有数据，不能删除
            throw new CustomizeExceptiion(20001, "有小节数据，不能删除");
        } else {//没有数据，可以删除
            int result = baseMapper.deleteById(chapterID);
            return result > 0;
        }
    }

    /**
     * 根据课程id删除章节
     *
     * @param courseId
     */
    @Override
    public void removeByCourseId(String courseId) {
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course-id", courseId);
        baseMapper.delete(wrapper);
    }
}

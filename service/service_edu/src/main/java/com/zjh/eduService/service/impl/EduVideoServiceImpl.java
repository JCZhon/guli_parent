package com.zjh.eduService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjh.commonUtils.R;
import com.zjh.eduService.client.VodClient;
import com.zjh.eduService.entity.EduVideo;
import com.zjh.eduService.mapper.EduVideoMapper;
import com.zjh.eduService.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程小节（视频） 服务实现类
 * </p>
 *
 * @author zjh
 * @since 2020-12-23
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private VodClient vodClient;

    /**
     * 根据课程id删除小节 同时要删除多个章节的视频
     *
     * @param courseId
     */
    @Override
    public void removeByCourseId(String courseId) {
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id", courseId);
        wrapperVideo.select("video_sourse_id");//只查这一个值，效率更高
        List<EduVideo> eduVideoList = baseMapper.selectList(wrapperVideo);
        List<String> videoIds = new ArrayList<>();
        for (EduVideo eduVideo :
                eduVideoList) {
            String videoSourseId = eduVideo.getVideoSourceId();
            if (!StringUtils.isEmpty(videoSourseId)) {
                videoIds.add(videoSourseId);
            }
        }
        if (videoIds.size() > 0) {
            vodClient.deleteBatch(videoIds);
        }
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        baseMapper.delete(wrapper);
    }

    /**
     * 根据小节id删除小节 同时要删除对应阿里云中的视频
     *
     * @param id
     * @return
     */
    @Override
    public R removeVideoById(String id) {
        EduVideo eduVideo = baseMapper.selectById(id);
        String videoSourceId = eduVideo.getVideoSourceId();
        if ((!StringUtils.isEmpty(videoSourceId))) {
            vodClient.removeVideo(videoSourceId);
        }
        baseMapper.deleteById(id);
        return R.ok();
    }
}

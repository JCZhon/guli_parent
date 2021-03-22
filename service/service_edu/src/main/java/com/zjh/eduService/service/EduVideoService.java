package com.zjh.eduService.service;

import com.zjh.commonUtils.R;
import com.zjh.eduService.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程小节（视频） 服务类
 * </p>
 *
 * @author zjh
 * @since 2020-12-23
 */
public interface EduVideoService extends IService<EduVideo> {

    void removeByCourseId(String courseId);

    R removeVideoById(String id);
}

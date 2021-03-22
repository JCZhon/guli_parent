package com.zjh.eduService.controller;


import com.alibaba.excel.EasyExcel;
import com.zjh.commonUtils.ExcelUtils;
import com.zjh.commonUtils.LocalDateConcerter;
import com.zjh.commonUtils.R;
import com.zjh.eduService.entity.EduChapter;
import com.zjh.eduService.entity.chapter.ChapterVo;
import com.zjh.eduService.service.EduChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程大纲列表
 * </p>
 *
 * @author zjh
 * @since 2020-12-23
 */
@RestController
@RequestMapping("/eduService/edu-chapter")
@Api("课程大纲")
public class EduChapterController {

    @Autowired
    private EduChapterService eduChapterService;

    /**
     * 课程大纲列表（查询）
     *
     * @param courseID
     * @return
     */
    @GetMapping("addChapterVideo/{courseID}")
    @ApiOperation("课程大纲列表")
    public R AddChapterVideo(@ApiParam(name = "courseID", value = "课程ID", required = true)
                             @PathVariable String courseID,
                             HttpServletResponse response) throws IOException {
        List<ChapterVo> list = eduChapterService.addChapterVideo(courseID);
        ExcelUtils.setResponse(response, "课程大纲列表");
        EasyExcel.write(response.getOutputStream(),ChapterVo.class).sheet("课程大纲列表").registerConverter(new LocalDateConcerter()).doWrite(list);
        return R.ok().data("allChapterVideo", list);
    }

    /**
     * 添加章节
     */
    @PostMapping("addChapter")
    @ApiOperation("添加章节")
    public R addChapter(@RequestBody EduChapter eduChapter) {
        eduChapterService.save(eduChapter);
        return R.ok();
    }

    /**
     * 修改章节
     */
    @PostMapping("updateChapter")
    @ApiOperation("修改章节")
    public R updateChapter(@RequestBody EduChapter eduChapter) {
        eduChapterService.updateById(eduChapter);
        return R.ok();
    }

    /**
     * 根据章节id查询
     */
    @GetMapping("selectById/{chapterID}")
    @ApiOperation("根据章节id查询")
    //@Transactional(readOnly = true)只读事务作用于报表
    public R selectById(@ApiParam(name = "chapterId", value = "章节id", required = true)
                        @PathVariable String chapterID) {
        EduChapter eduChapter = eduChapterService.getById(chapterID);
        List<EduChapter> list = new ArrayList<>();
        list.add(eduChapter);
        String fileName = "D:\\EduChapter.xlsx";
        EasyExcel.write(fileName, EduChapter.class).sheet("测试一下").doWrite(list);
        return R.ok().data("eduChapter", eduChapter);
    }

    /**
     * 删除章节
     * 有两种情况：1.章节下面没有小节-->直接删除
     * 2.章节下面有小节-->必须先删除小节才能删除章节（这种情况有两种解决方式，这里使用这一种）
     * 另一种方式：直接全部删除（2种方法都可以）
     */
    @DeleteMapping("deleteChapter/{chapterID}")
    @ApiOperation("删除章节")
    public R deleteChapter(@ApiParam(name = "chapterID", value = "（小节表的chapter_id）:章节id", required = true)
                           @PathVariable String chapterID) {
        boolean flag = eduChapterService.deleteChapter(chapterID);
        return R.judgeByBo(flag);

    }


}


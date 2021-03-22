package com.zjh.eduService.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjh.commonUtils.R;
import com.zjh.eduService.entity.EduTeacher;
import com.zjh.eduService.entity.vo.TeacherQuery;
import com.zjh.eduService.service.EduTeacherService;
import com.zjh.servicebase.exception.CustomizeExceptiion;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师模块
 * </p>
 *
 * @author zjh
 * @since 2020-12-15
 */
@Api("后台讲师管理")
@RestController
@RequestMapping("/eduService/teacher")
@CrossOrigin
public class EduTeacherController {

    @Autowired
    private EduTeacherService teacherService;

    /**
     * 查询全部讲师
     *
     * @return
     */
    @ApiOperation("查询全部讲师")
    @GetMapping("find")
    public R findAllTeacher() {
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("items", list);
    }

    /**
     * 讲师逻辑删除功能
     */
    @ApiOperation("讲师逻辑删除")
    @DeleteMapping("{id}")
    public R removeTeacher(@ApiParam(name = "id", value = "讲师id", required = true) @PathVariable String id) {//获取路径中的id值
        boolean flag = teacherService.removeById(id);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 分页查询讲师的方法
     * current-当前页
     * limit-每页记录数
     */
    @GetMapping("pageTeacher/{current}/{limit}")
    @ApiOperation("分页查询讲师")
    public R pageListTeacher(@ApiParam(name = "current", value = "当前页") @PathVariable long current,
                             @ApiParam(name = "limit", value = "每页记录数") @PathVariable long limit) {

        try {
            int i = 10 / 0;
        } catch (Exception e) {
            //执行自定义异常
            throw new CustomizeExceptiion(20001, "执行了自定义异常处理");
        }


        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);

        //调用方法实现分页
        //调用方法的时候，底层会把分页所有的数据封装到pageTeacher对象里面（这时候查询到的数据也在里面）
        teacherService.page(pageTeacher, null);

        long total = pageTeacher.getTotal();//总记录数
        List<EduTeacher> record = pageTeacher.getRecords();//数据list集合

        Map map = new HashMap<>();
        map.put("total", total);
        map.put("record", record);
        return R.ok().data(map);
        //下面这种方法也可以，自己选择
        //return R.ok().data("total",total).data("record",record);
    }

    /**
     * 条件查询带分页的方法
     */
    @ApiOperation("分页条件查询讲师")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current,
                                  @PathVariable long limit,
                                  @RequestBody(required = false) TeacherQuery teacherQuery) {
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String start = teacherQuery.getStart();
        String end = teacherQuery.getEnd();
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            wrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(start)) {
            wrapper.ge("gmt_create", start);
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create", end);
        }

        //对查询结构按时间进行降序排序，这样的话新插入的数据会显示在前面
        wrapper.orderByDesc("gmt_create");

        teacherService.page(pageTeacher, wrapper);
        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();
        return R.ok().data("total", total).data("rows", records);

    }

    /**
     * 添加讲师接口的方法
     */
    @PostMapping("addTeacher")
    @ApiOperation("添加讲师")
    public R addTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean save = teacherService.save(eduTeacher);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }   //这里可以改成三目运算
    }

    /**
     * 根据讲师id查询
     */
    @ApiOperation("根据讲师id查询")
    @GetMapping("getById/{id}")
    public R getById(@PathVariable String id) {
        EduTeacher teacher = teacherService.getById(id);
        return R.ok().data("teacher", teacher);
    }

    /**
     * 讲师修改功能
     */
    @ApiOperation("讲师修改功能")
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean flag = teacherService.updateById(eduTeacher);
        return R.judgeByBo(flag);
    }

}


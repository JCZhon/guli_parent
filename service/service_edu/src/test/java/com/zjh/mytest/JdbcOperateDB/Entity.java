package com.zjh.mytest.JdbcOperateDB;

import io.swagger.models.auth.In;
import lombok.Data;

import java.sql.Date;

/**
 * java模型
 */
@Data
public class Entity {
    private String id;
    private String title;
    private String parentId;
    private Integer sort;
    private Date gmtCreate;
    private Date gmtModified;
}

package com.zjh.mytest.JdbcOperateDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * dao层
 * 获得结果集并封装成java对象
 */
public class Dao {
    public Entity get(Integer index, String id) throws SQLException {
        Entity entity = null;
        //获取连接
        Connection conn = DbUtil.getConnection();

        //拼接sql
        String sql = "select * from project01 where id=?";


        //预编译sql，减少sql执行
        PreparedStatement pre = conn.prepareStatement(sql);
        //传参
        pre.setString(index, id);
        //执行获取结果集
        ResultSet result = pre.executeQuery();
        //ResultSet result=conn.prepareStatement(sql).executeQuery();   直接编译方式，选择预编译
        while (result.next()) {
            entity = new Entity();
            entity.setId(result.getString("id"));
            entity.setTitle(result.getString("title"));
            entity.setParentId(result.getString("parentId"));
            entity.setSort(result.getInt("sort"));
            entity.setGmtCreate(result.getDate("gmtCreate"));
            entity.setGmtModified(result.getDate("gmtModified"));
        }
        return entity;
    }


}

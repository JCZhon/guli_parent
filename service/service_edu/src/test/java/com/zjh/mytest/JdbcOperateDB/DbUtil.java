package com.zjh.mytest.JdbcOperateDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 获取数据库连接
 * conn
 */
public class DbUtil {
    public static final String URL = "jabc:mysql://localhost:3306/project01";
    public static final String USER = "root";
    public static final String PASSWORD = "12138";
    private static Connection conn = null;

    //加载驱动程序
    static {
        try {
            Class.forName("com.mysql.cj.jabc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    //提供静态方法方便调用创建连接
    public static Connection getConnection() {
        return conn;
    }

}

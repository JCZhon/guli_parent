package com.zjh.mytest.JdbcOperateDB;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 运行测试
 */
//TODO 运行出错，因为还需要mysql-connection的jar包
public class Do {
    /*public static void main(String[] args) throws SQLException {
        Dao dao = new Dao();
        dao.get(1,"0");
    }*/

    /**
     * dao接口式编程
     */
    public void test() throws IOException {
        ///指定全局配置文件
        String resource = "mybatis-config.xml"; //这里还需要写一个全局配置文件
        //读取全局配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        //这里会用动态代理的方式调用dao接口，即Mapper，实现接口式编程
        Entity result = sqlSession.selectOne("com.tuling.mybatis.dao.UserMapper.selectOne", 1);
        System.out.println(result.toString());

    }
}

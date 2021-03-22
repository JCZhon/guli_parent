package com.zjh.eduService.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * mybatisplus的配置类
 * 这个是旧版本的插件，新版本不需要这些插件也能用
 *
 * @Author zjh
 * @time 2020.12.10
 */

@Configuration
@MapperScan("com.zjh.eduService.mapper")
public class MpConfig {

    /**
     * 乐观锁插件
     */

    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }


    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 逻辑删除插件
     */
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

    /**
     * SQL 执行性能分析插件
     * 用于分析sql执行时间和具体的sql内容，便于优化
     * 开发环境使用，线上不推荐。 maxTime 指的是 sql 最大执行时长
     * <p>
     * 三种环境
     * * dev：开发环境
     * * test：测试环境
     * * prod：生产环境
     */
    @Bean
    @Profile({"dev", "test"})// 设置 dev test 环境开启(就是设置当前插件对哪个环境起作用)
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        performanceInterceptor.setMaxTime(100);//ms，超过此处设置的时间则sql不执行
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }


}

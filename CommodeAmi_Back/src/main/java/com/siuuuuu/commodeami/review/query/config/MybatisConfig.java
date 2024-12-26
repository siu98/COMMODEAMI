package com.siuuuuu.commodeami.review.query.config;

import com.siuuuuu.commodeami.review.query.repository.ReviewMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan(basePackages="com.siuuuuu.commodeami.review.query.repository", annotationClass = Mapper.class)
@Configuration("ReviewMybatisConfiguration")
public class MybatisConfig {
    private ReviewMapper reviewMapper;
}

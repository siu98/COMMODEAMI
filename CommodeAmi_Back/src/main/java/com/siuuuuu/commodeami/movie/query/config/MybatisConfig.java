package com.siuuuuu.commodeami.movie.query.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan(basePackages = "com.siuuuuu.commodeami.movie.query.repository", annotationClass = Mapper.class)
@Configuration("MovieMybatisConfiguration")
public class MybatisConfig {
}

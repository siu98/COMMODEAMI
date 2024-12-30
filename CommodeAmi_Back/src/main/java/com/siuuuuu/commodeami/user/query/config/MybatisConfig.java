package com.siuuuuu.commodeami.user.query.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan(basePackages = "com.siuuuuu.commodeami.user.query.repository", annotationClass = Mapper.class )
@Configuration("UserMybatisConfiguration")
public class MybatisConfig {
}

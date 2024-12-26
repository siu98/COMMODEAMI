package com.siuuuuu.commodeami.averagescope.query.config;

import com.siuuuuu.commodeami.averagescope.query.repository.AverageScopeMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan(basePackages = "com.siuuuuu.commodeami.averagescope.query.repository", annotationClass = Mapper.class)
@Configuration("AverageScopeMybatisConfiguration")
public class MybatisConfig {
    private AverageScopeMapper averageScopeMapper;
}

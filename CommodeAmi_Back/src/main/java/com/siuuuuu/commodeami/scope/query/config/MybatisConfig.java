package com.siuuuuu.commodeami.scope.query.config;

import com.siuuuuu.commodeami.scope.query.repository.ScopeMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan(basePackages = "com.siuuuuu.commodeami.scope.query.repository", annotationClass = Mapper.class)
@Configuration("ScopeMybatisConfiguration")
public class MybatisConfig {
    private ScopeMapper scopeMapper;
}

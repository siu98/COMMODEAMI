package com.siuuuuu.commodeami.customticket.query.config;

import com.siuuuuu.commodeami.customticket.query.repository.CustomTIcketMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan(basePackages = "com.siuuuuu.commodeami.customticket.query.repository", annotationClass = Mapper.class)
@Configuration("CustomTicketMybatisConfiguration")
public class MybatisConfig {
    private CustomTIcketMapper customTicketMapper;
}

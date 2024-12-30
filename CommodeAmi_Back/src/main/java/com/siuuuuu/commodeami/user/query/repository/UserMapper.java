package com.siuuuuu.commodeami.user.query.repository;

import com.siuuuuu.commodeami.user.command.aggregate.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User findByEmail(String username);

    User findByUserId(Long userId);
}

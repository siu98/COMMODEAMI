package com.siuuuuu.commodeami.user.query.service;

import com.siuuuuu.commodeami.user.command.aggregate.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDTO findByEmail(String email);

    UserDTO findById(Long userId);

}

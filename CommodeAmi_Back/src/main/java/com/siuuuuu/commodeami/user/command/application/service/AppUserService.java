package com.siuuuuu.commodeami.user.command.application.service;

import com.siuuuuu.commodeami.user.command.aggregate.dto.UserDTO;

public interface AppUserService {
    void registUser(UserDTO newUser);

    void updatePassword(Long userId, String currentPwd, String newPwd);

    void updateLastAccessTime(String email);
//    UserDetails loadUserByUsername(String userId);
}

package com.siuuuuu.commodeami.user.command.aggregate.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
@ToString
public class CustomUser extends org.springframework.security.core.userdetails.User {

    private Long userId;

    private String userName;

    private String userEmail;

    private String profile;

    private String nickName;

    public CustomUser(User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getEmail(), user.getPassword(), authorities);
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.profile = user.getProfile();
        this.nickName = user.getNickname();
    }


}

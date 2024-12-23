package com.siuuuuu.commodeami.user.security;

import com.siuuuuu.commodeami.user.command.application.service.UserService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtUtil {

    private final Key secretKey;
    private UserService userService;

    public JwtUtil(@Value("${token.secret}") String secretKey,
                   UserService userService) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
        this.userService = userService;
    }

    /* 설명. Token 검증(Bearer 토큰이 넘어왔고, 우리 사이트의 secret key로 만들어 졌는가, 만료되었는지와 내용이 비어있진 않은지) */
    public Boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e){
            log.info("Invalid JWT token");
        } catch (ExpiredJwtException e){
            log.info("Expired JWT token");
        } catch (UnsupportedJwtException e){
            log.info("Unsupported JWT token");
        } catch (IllegalArgumentException e){
            log.info("JWT claims string is empty.");
        }
        return true;
    }

    /* 설명. 넘어온 AccessToken으로 인증 객체 추출 */
    public Authentication getAuthentication(String token) {

        /* 설명. 토큰을 들고 왔던 들고 오지 않았던(로그인 시) 동일하게 security가 관리 할 UserDetails 타입을 정의 */
        UserDetails userDetails = userService.loadUserByUsername(this.getUserId(token));

        /* 설명 토큰에서 claim들 추출 */
        Claims claims = parseClaims(token);
        log.info("넘어온 Access Token claims 확인: {}", claims );

        Collection<? extends GrantedAuthority> authorities = null;
        if (claims.get("auth") == null) {
            throw new RuntimeException("권한 정보가 없는 토큰 입니다.");
        } else {
            /* 설명. claim에서 권한 정보를 가져오기 */
            authorities = Arrays.stream(claims.get("auth").toString()
                    .replace("[","")
                    .replace("]", "")
                    .split(", "))
                    .map(role -> new SimpleGrantedAuthority(role))
                    .collect(Collectors.toList());
        }
        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }

    /* 설명. Token에서 Claims 추출 */
    public Claims parseClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
    }

    /* 설명. token에서 사용자의 id(subject 클레임) 추출 */
    public String getUserId(String token) {
        return parseClaims(token).getSubject();
    }

}
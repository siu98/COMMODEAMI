//package com.siuuuuu.commodeami.user.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurity {
//
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//    private Environment env;
//    private JwtUtil jwtUtil;
//
//    // 의존성 주입
//    @Autowired
//    public WebSecurity(BCryptPasswordEncoder bCryptPasswordEncoder,
//                       Environment env,
//                       JwtUtil jwtUtil) {
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//        this.env = env;
//        this.jwtUtil = jwtUtil;
//    }
//
//    @Bean
//    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
//
//        http.csrf((csrf) -> csrf.disable());
//
//        AuthenticationManagerBuilder authenticationManagerBuilder =
//                http.getSharedObject(AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder.userDetailsService(userService)
//                .passwordEncoder(bCryptPasswordEncoder);
//
//        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
//
//        http.authorizeHttpRequests((authz) >
//                authz.requestMatchers(new AntPathRequestMatcher("/users/**")))
//    }
//}

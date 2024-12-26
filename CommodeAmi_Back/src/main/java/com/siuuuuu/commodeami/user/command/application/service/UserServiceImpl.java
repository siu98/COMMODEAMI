package com.siuuuuu.commodeami.user.command.application.service;

import com.siuuuuu.commodeami.user.command.domain.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

//@Service
//public class UserServiceImpl implements UserService {
//
//    UserRepository userRepository;
//    ModelMapper modelMapper;
//
//    BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Autowired
//    public UserServiceImpl(UserRepository userRepository,
//                           ModelMapper modelMapper,
//                           BCryptPasswordEncoder bCryptPasswordEncoder) {
//        this.userRepository = userRepository;
//        this.modelMapper = modelMapper;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//    }
//}

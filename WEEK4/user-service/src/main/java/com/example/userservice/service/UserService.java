package com.example.userservice.service;

import com.example.userservice.dto.UserDto;
import com.example.userservice.jpa.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto userDto);
    UserDto getUserByUserId(String userId); // 개별 사용자의 상세 보기
    Iterable<UserEntity> getUserByAll(); // 전체 사용자 목록 조회

    UserDto getUserDetailByEmail(String email);
}

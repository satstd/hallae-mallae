package com.java.hallaemallae.domain.user.service.interfaces;

import com.java.hallaemallae.domain.user.dto.UserDetail;
import com.java.hallaemallae.domain.user.dto.UserRequestDto;
import com.java.hallaemallae.domain.user.entity.User;

public interface UserService {
    User signUp(UserRequestDto request);
    User findUserByUsername(String username);
    User updateUser(UserDetail userDetail, UserRequestDto request);
}

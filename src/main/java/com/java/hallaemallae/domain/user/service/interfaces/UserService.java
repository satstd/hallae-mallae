package com.java.hallaemallae.domain.user.service.interfaces;

import com.java.hallaemallae.domain.user.dto.UserDetail;
import com.java.hallaemallae.domain.user.dto.UserRequestDto;
import com.java.hallaemallae.domain.user.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User signUp(UserRequestDto request);
    User findUserByUsername(String username);
    User updateUser(UserDetail userDetail, UserRequestDto request);

    void deleteMember(UserDetail userDetail);
}

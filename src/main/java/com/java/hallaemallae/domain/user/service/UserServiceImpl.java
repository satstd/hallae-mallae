package com.java.hallaemallae.domain.user.service;

import com.java.hallaemallae.domain.user.dto.UserDetail;
import com.java.hallaemallae.domain.user.dto.UserRequestDto;
import com.java.hallaemallae.domain.user.entity.User;
import com.java.hallaemallae.domain.user.repository.UserRepository;
import com.java.hallaemallae.domain.user.service.interfaces.UserService;
import com.java.hallaemallae.global.common.ErrorCode;
import com.java.hallaemallae.global.exception.UserAlreadyExistException;
import com.java.hallaemallae.global.exception.UserNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public User signUp(UserRequestDto request) {
        boolean exists = userRepository.existsUserByUsername(request.getUsername());
        if (exists) {
            throw new UserAlreadyExistException(ErrorCode.USER_ALREADY_EXIST);
        }
        User user = User.createUser(request, passwordEncoder);
        return userRepository.save(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND));
    }

    @Override
    @Transactional
    public User updateUser(UserDetail userDetail, UserRequestDto request) {
        User user = userRepository.findById(userDetail.getUserId())
                .orElseThrow(() -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND));
        user.changePassword(request.getPassword());
        user.changeNickname(request.getNickname());
        return user;
    }

    @Override
    public void deleteMember(UserDetail userDetail) {
        userRepository.deleteById(userDetail.getUserId());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserByUsername(username);
        return new UserDetail(user.getUserId(), user.getUsername(),
                user.getPassword(), user.getNickname(), user.getRole());
    }
}

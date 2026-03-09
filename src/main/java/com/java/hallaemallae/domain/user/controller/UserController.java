package com.java.hallaemallae.domain.user.controller;

import com.java.hallaemallae.domain.user.dto.*;
import com.java.hallaemallae.domain.user.entity.User;
import com.java.hallaemallae.domain.user.service.interfaces.UserService;
import com.java.hallaemallae.global.common.APIResponse;
import com.java.hallaemallae.global.exception.UserAlreadyExistException;
import com.java.hallaemallae.global.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<APIResponse<UserResponseDto>> createUser(@RequestBody UserRequestDto userDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(
                APIResponse.create(UserResponseDto.of(userService.signUp(userDto)))
        );
    }

    @PutMapping("me")
    public ResponseEntity<APIResponse<UserResponseDto>> updateUser(@AuthenticationPrincipal UserDetail userDetail,
                                                       @RequestBody UserRequestDto userDto) {
        return ResponseEntity.ok().body(
                APIResponse.success(UserResponseDto.of(userService.updateUser(userDetail, userDto)))
        );
    }

    @DeleteMapping("me")
    public ResponseEntity<APIResponse<Void>> deleteUser(@AuthenticationPrincipal UserDetail userDetail) {
        userService.deleteMember(userDetail);
        return ResponseEntity.ok()
                .body(APIResponse.success());
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<APIResponse<ErrorResponse>> userAlreadyExistException(UserAlreadyExistException e) {
        return ResponseEntity.status(e.getErrorCode().getCode()).body(
                APIResponse.fail(e.getErrorCode(), e.getMessage())
        );
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<APIResponse<ErrorResponse>> userNotFoundException(UserNotFoundException e) {
        return ResponseEntity.status(e.getErrorCode().getCode()).body(
                APIResponse.fail(e.getErrorCode(), e.getMessage())
        );
    }
}

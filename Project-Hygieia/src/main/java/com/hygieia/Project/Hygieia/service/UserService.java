package com.hygieia.Project.Hygieia.service;

import com.hygieia.Project.Hygieia.dto.UserLoginResponse;
import com.hygieia.Project.Hygieia.dto.UserRegisterRequest;
import com.hygieia.Project.Hygieia.dto.UserResponse;


public interface UserService {
    String registerUser(UserRegisterRequest userRegisterRequest);

    UserResponse findUserById(Long id) throws Exception;

    UserResponse findUserByEmail(String email) throws Exception;

    UserLoginResponse loginUser(String email, String password);
}

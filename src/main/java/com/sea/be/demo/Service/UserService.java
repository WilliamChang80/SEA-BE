package com.sea.be.demo.Service;

import com.sea.be.demo.Dto.AuthenticationRequest;
import com.sea.be.demo.Dto.UserRequest;
import com.sea.be.demo.Dto.UserResponse;
import com.sea.be.demo.Entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService extends UserDetailsService {
    void createUser(AuthenticationRequest authenticationRequest);

    void updateUser(UserRequest userRequest, Long id) throws Exception;

    User getUserByUserName(String userName);

    User getUserById(Long userId);

    boolean isValidLoginCredentials(String userName, String password);

    List<UserResponse> getAllUsers();
}

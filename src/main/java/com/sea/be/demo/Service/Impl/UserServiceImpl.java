package com.sea.be.demo.Service.Impl;

import com.sea.be.demo.Config.SecurityConfigurer;
import com.sea.be.demo.Dto.AuthenticationRequest;
import com.sea.be.demo.Dto.UserResponse;
import com.sea.be.demo.Entity.User;
import com.sea.be.demo.Repository.UserRepository;
import com.sea.be.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(AuthenticationRequest request) {
        String hashedPassword = BCrypt.hashpw(request.getPassword(), BCrypt.gensalt(10));
        User user = User.builder().name(request.getUserName()).password(hashedPassword).build();
        userRepository.save(user);
    }

    @Override
    public void updateUser(AuthenticationRequest request, Long id) throws Exception {
        String hashedPassword = new BCryptPasswordEncoder().encode(request.getPassword());
        try {
            User user = userRepository.findById(id).orElse(null);
            user.setName(request.getUserName());
            user.setPassword(hashedPassword);
            userRepository.save(user);
        } catch (EntityNotFoundException e) {
            throw new Exception("User Not Found!", e);
        }
    }

    @Override
    public User getUserByUserName(String userName) {
        return userRepository.getByNameEquals(userName);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public boolean isValidLoginCredentials(String userName, String password) {
        User user = userRepository.getByNameEquals(userName);
        if (user == null) {
            return false;
        }
        return BCrypt.checkpw(password, user.getPassword());
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.getByNameEquals(userName);
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
                new ArrayList<>());
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponseList = users.stream().map(this::convertUserToResponse)
                .collect(Collectors.toList());
        System.out.println(userResponseList.size());
        return userResponseList;
    }

    private UserResponse convertUserToResponse(User user) {
        return UserResponse.builder().id(user.getId()).username(user.getName()).build();
    }
}

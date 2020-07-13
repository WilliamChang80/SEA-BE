package com.sea.be.demo.Service.Impl;

import com.sea.be.demo.Config.SecurityConfigurer;
import com.sea.be.demo.Dto.AuthenticationRequest;
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

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

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
}

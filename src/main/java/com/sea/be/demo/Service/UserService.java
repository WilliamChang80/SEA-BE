package com.sea.be.demo.Service;

import com.sea.be.demo.Dto.AuthenticationRequest;
import com.sea.be.demo.Entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public void createUser(AuthenticationRequest authenticationRequest);

    public void updateUser(AuthenticationRequest authenticationRequest, Long id) throws Exception;

    public User getUserByUserName(String userName);

    public User getUserById(Long userId);

    public boolean isValidLoginCredentials(String userName, String password);
}

package com.sea.be.demo.Service;

import com.sea.be.demo.Dto.AuthenticationRequest;
import com.sea.be.demo.Entity.User;

public interface UserService {
    public void createUser(AuthenticationRequest authenticationRequest);

    public void updateUser(AuthenticationRequest authenticationRequest, Long id) throws Exception;

    public User getUserByUserName(String userName);

    public User getUserById(Long userId);
}

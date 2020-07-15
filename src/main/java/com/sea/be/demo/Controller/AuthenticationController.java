package com.sea.be.demo.Controller;

import com.sea.be.demo.Config.JwtUtil;
import com.sea.be.demo.Dto.AuthenticationRequest;
import com.sea.be.demo.Dto.AuthenticationResponse;
import com.sea.be.demo.Dto.BaseResponse;
import com.sea.be.demo.Entity.User;
import com.sea.be.demo.Enum.HttpResponse;
import com.sea.be.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private AuthenticationManager authenticationManager;

    private JwtUtil jwtUtil;

    private UserService userService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtUtil jwtUtil,
                                    UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/login")
    public BaseResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
            throws Exception {
        try {
            if (!userService.isValidLoginCredentials(authenticationRequest.getUserName(),
                    authenticationRequest.getPassword())) {
                throw new BadCredentialsException("Username and password not match !");
            }
        } catch (BadCredentialsException e) {
            return BaseResponse.builder().code(HttpResponse.UNAUTHORIZED.getCode()).message(e.getMessage()).build();
        }
        UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUserName());
        String jwt = jwtUtil.generateToken(userDetails);
        BaseResponse response = BaseResponse.builder().code(HttpResponse.SUCCESS.getCode()).message("Success").data(
                AuthenticationResponse.builder().token(jwt).build()).build();
        return response;
    }

    @PostMapping("/register")
    public BaseResponse register(@RequestBody AuthenticationRequest authenticationRequest) {
        if (!usernameValid(authenticationRequest.getUserName())) {
            return BaseResponse.builder().code(HttpResponse.UNPROCESSABLE_ENTITY.getCode()).message(
                    "Username Already Taken!")
                    .build();
        }
        userService.createUser(authenticationRequest);
        return BaseResponse.builder().code(HttpResponse.SUCCESS.getCode()).message("Success")
                .build();
    }

    private boolean usernameValid(String userName) {
        return userService.getUserByUserName(userName) == null;
    }
}

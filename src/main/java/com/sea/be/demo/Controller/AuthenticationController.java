package com.sea.be.demo.Controller;

import com.sea.be.demo.Config.CustomUserDetailService;
import com.sea.be.demo.Config.JwtUtil;
import com.sea.be.demo.Dto.AuthenticationRequest;
import com.sea.be.demo.Dto.AuthenticationResponse;
import com.sea.be.demo.Dto.BaseResponse;
import com.sea.be.demo.Entity.User;
import com.sea.be.demo.Enum.HttpResponse;
import com.sea.be.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private AuthenticationManager authenticationManager;

    private CustomUserDetailService userDetailService;

    private JwtUtil jwtUtil;

    private UserService userService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, CustomUserDetailService
            userDetailsService, JwtUtil jwtUtil, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userDetailService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/authenticate")
    public BaseResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
            throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest
                    .getUserName(),
                    authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Username and password not match !", e);
        }
        final UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getUserName());
        final String jwt = jwtUtil.generateToken(userDetails);
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

    private boolean updatedUsernameValid(String userName, Long userId) {
        User user = userService.getUserById(userId);
        if (userName != user.getName()) {
            return userService.getUserByUserName(userName).getId() == userId;
        }
        return true;
    }

    @PutMapping("/update/{id}")
    public BaseResponse updateUser(@RequestBody AuthenticationRequest authenticationRequest, @PathVariable Long id)
            throws Exception {
        if (!updatedUsernameValid(authenticationRequest.getUserName(), id)) {
            return BaseResponse.builder().code(HttpResponse.UNPROCESSABLE_ENTITY.getCode()).message(
                    "Username Already Taken!")
                    .build();
        }
        userService.updateUser(authenticationRequest, id);
        return BaseResponse.builder().code(HttpResponse.SUCCESS.getCode()).message("Success")
                .build();
    }
}

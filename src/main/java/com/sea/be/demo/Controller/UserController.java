package com.sea.be.demo.Controller;

import com.sea.be.demo.Dto.AuthenticationRequest;
import com.sea.be.demo.Dto.BaseResponse;
import com.sea.be.demo.Dto.UserResponse;
import com.sea.be.demo.Entity.User;
import com.sea.be.demo.Enum.HttpResponse;
import com.sea.be.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1")
@RestController
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/user/update/{id}")
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

    @GetMapping("/users")
    public BaseResponse getAllUsers() {
        List<UserResponse> users = userService.getAllUsers();
        return BaseResponse.builder().code(HttpResponse.SUCCESS.getCode()).message("Success").data(users)
                .build();
    }

    private boolean updatedUsernameValid(String userName, Long userId) {
        User user = userService.getUserById(userId);
        if (userName != user.getName()) {
            User userTemp = userService.getUserByUserName(userName);
            if(userTemp == null || userTemp.getId() == userId) {
                return true;
            }
            return false;
        }
        return true;
    }
}

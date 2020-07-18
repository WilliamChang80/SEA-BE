package com.sea.be.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String newUserName;
    private String oldUserName;
    private String newPassword;
    private String oldPassword;
}

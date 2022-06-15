package com.example.userservice.controller.dto;

import com.example.userservice.controller.vo.ResponseOrder;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private String name;
    private String email;
    private String pwd;
    private String userId;
    private String createDate;

    private String encryptPwd;

    private List<ResponseOrder> orders;

}

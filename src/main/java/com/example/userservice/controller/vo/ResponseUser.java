package com.example.userservice.controller.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseUser {
    private String name;
    private String email;
    private String pwd;
    private String userId;

    private List<ResponseOrder> orders;
}

package com.example.userservice.controller.vo;



import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RequestUser {
    private String email;
    @NotNull(message = "name is not null")
    @Size(min = 2, message = "name siz > 2")
    private String name;
    @NotNull(message = "pwd is not null")
    @Size(min = 2, message = "pwd siz > 2")
    private String pwd;
}

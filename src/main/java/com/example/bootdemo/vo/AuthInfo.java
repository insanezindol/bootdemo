package com.example.bootdemo.vo;

import lombok.Data;

@Data
public class AuthInfo {
    private String username;
    private String password;
    private String authority;
    private int enabled;
    private String name;
}

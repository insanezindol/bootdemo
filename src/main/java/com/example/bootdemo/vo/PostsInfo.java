package com.example.bootdemo.vo;

import lombok.Data;

@Data
public class PostsInfo {
    private String id;
    private String subject;
    private String content;
    private String created;
    private String user_name;
    private String hit;
}

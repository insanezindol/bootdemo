package com.example.bootdemo.vo;

import lombok.Data;

@Data
public class ErrorInfo {
    private String code;
    private String data;
    private String msg;

    public ErrorInfo(String code, String data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }
}
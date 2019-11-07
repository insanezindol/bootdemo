package com.example.bootdemo.controller;

import com.example.bootdemo.vo.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/my")
@Slf4j
public class MyController {

    @GetMapping(value = "/mypage")
    public JSONObject mypage(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        // 로그인 사용자 정보 출력
        log.info("=========================");
        log.info(customUserDetails.getUsername());
        log.info(customUserDetails.getName());
        log.info("=========================");

        JSONObject obj = new JSONObject();

        obj.put("code", "100200");
        obj.put("msg", "success");
        obj.put("data", null);
        return obj;
    }

}

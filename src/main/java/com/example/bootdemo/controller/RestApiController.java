package com.example.bootdemo.controller;

import com.example.bootdemo.config.LogExecutionTime;
import com.example.bootdemo.service.ApiService;
import com.example.bootdemo.service.PostsService;
import com.example.bootdemo.vo.PostsInfo;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
@Slf4j
public class RestApiController {

    @Autowired
    private PostsService postsService;

    @Autowired
    private ApiService apiService;

    @LogExecutionTime
    @GetMapping(value = "/resource")
    public JSONObject list() {
        JSONObject obj = new JSONObject();

        List<PostsInfo> list = postsService.getList();

        obj.put("code", "100200");
        obj.put("msg", "success");
        obj.put("data", list);
        return obj;
    }

    @GetMapping(value = "/resource/{id}")
    public JSONObject get(@PathVariable String id) {
        PostsInfo param = new PostsInfo();
        param.setId(id);
        PostsInfo info = postsService.getOne(param);

        JSONObject obj = new JSONObject();
        if (info == null) {
            obj.put("code", "100103");
            obj.put("msg", "object not exist");
            obj.put("data", null);
        } else {
            obj.put("code", "100200");
            obj.put("msg", "success");
            obj.put("data", info);
        }
        return obj;
    }

    @PostMapping(value = "/resource")
    public JSONObject add(@RequestBody PostsInfo param) {
        int resultCnt = postsService.addPost(param);
        JSONObject output = new JSONObject();
        if (resultCnt == 1) {
            output.put("code", "100200");
            output.put("msg", "success");
        } else {
            output.put("code", "100103");
            output.put("msg", "error");
        }
        return output;
    }

    @PutMapping(value = "/resource")
    public JSONObject modify(@RequestBody PostsInfo param) {
        int resultCnt = postsService.modifyPost(param);
        JSONObject output = new JSONObject();
        if (resultCnt == 1) {
            output.put("code", "100200");
            output.put("msg", "success");
        } else {
            output.put("code", "100103");
            output.put("msg", "error");
        }
        return output;
    }

    @DeleteMapping(value = "/resource/{id}")
    public JSONObject delete(@PathVariable String id) {
        PostsInfo param = new PostsInfo();
        param.setId(id);
        int resultCnt = postsService.deletePost(param);

        JSONObject output = new JSONObject();
        if (resultCnt == 1) {
            output.put("code", "100200");
            output.put("msg", "success");
        } else {
            output.put("code", "100103");
            output.put("msg", "error");
        }
        return output;
    }

    @GetMapping(value = "/api")
    public JSONObject api() {
        JSONObject obj = new JSONObject();

        apiService.callUpxide();

        obj.put("code", "100200");
        obj.put("msg", "success");
        obj.put("data", null);
        return obj;
    }

}

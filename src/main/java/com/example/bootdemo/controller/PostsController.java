package com.example.bootdemo.controller;

import com.example.bootdemo.service.PostsService;
import com.example.bootdemo.vo.PostsInfo;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/post")
@Slf4j
public class PostsController {

    @Autowired
    private PostsService postsService;

    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public JSONObject list() {
        JSONObject obj = new JSONObject();

        List<PostsInfo> list = postsService.getList();

        obj.put("code", "100200");
        obj.put("msg", "success");
        obj.put("data", list);
        return obj;
    }

    @RequestMapping(value = "/get", method = {RequestMethod.GET, RequestMethod.POST})
    public JSONObject get(@RequestBody Map<String, Object> parameter) {
        String id = (String) parameter.get("id");

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

    @RequestMapping(value = "/add", method = {RequestMethod.GET, RequestMethod.POST})
    public JSONObject add(@RequestBody Map<String, Object> parameter) {
        String subject = (String) parameter.get("subject");
        String content = (String) parameter.get("content");
        String userName = (String) parameter.get("userName");

        PostsInfo param = new PostsInfo();
        param.setSubject(subject);
        param.setContent(content);
        param.setUser_name(userName);

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

    @RequestMapping(value = "/modify", method = {RequestMethod.GET, RequestMethod.POST})
    public JSONObject modify(@RequestBody Map<String, Object> parameter) {
        String id = (String) parameter.get("id");
        String subject = (String) parameter.get("subject");
        String content = (String) parameter.get("content");

        PostsInfo param = new PostsInfo();
        param.setId(id);
        param.setSubject(subject);
        param.setContent(content);

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

    @RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
    public JSONObject delete(@RequestBody Map<String, Object> parameter) {
        String id = (String) parameter.get("id");

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

}

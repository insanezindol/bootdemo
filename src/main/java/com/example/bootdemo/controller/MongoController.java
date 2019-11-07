package com.example.bootdemo.controller;

import com.example.bootdemo.service.MongoService;
import com.example.bootdemo.vo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mongo")
@Slf4j
public class MongoController {

    @Autowired
    MongoService mongoService;

    @GetMapping(value = "/resource")
    public JSONObject list() {
        List<UserInfo> list = mongoService.getList();

        JSONObject obj = new JSONObject();
        obj.put("code", "100200");
        obj.put("msg", "success");
        obj.put("data", list);
        return obj;
    }

    @GetMapping(value = "/resource/{name}")
    public JSONObject get(@PathVariable String name) {
        UserInfo info = mongoService.getOne(name);

        JSONObject obj = new JSONObject();
        obj.put("code", "100200");
        obj.put("msg", "success");
        obj.put("data", info);
        return obj;
    }

    @PostMapping(value = "/resource")
    public JSONObject save(@RequestBody UserInfo param) {
        mongoService.add(param);

        JSONObject output = new JSONObject();
        output.put("code", "100200");
        output.put("msg", "success");
        return output;
    }

    @PutMapping(value = "/resource")
    public JSONObject modify(@RequestBody UserInfo param) {
        mongoService.modify(param);

        JSONObject output = new JSONObject();
        output.put("code", "100200");
        output.put("msg", "success");
        return output;
    }

    @DeleteMapping(value = "/resource/{id}")
    public JSONObject delete(@PathVariable String id) {
        mongoService.delete(id);

        JSONObject output = new JSONObject();
        output.put("code", "100200");
        output.put("msg", "success");
        return output;
    }

}

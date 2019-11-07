package com.example.bootdemo.service;

import com.example.bootdemo.vo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MongoService {

    @Autowired
    MongoTemplate mongoTemplate;

    public List<UserInfo> getList() {
        return mongoTemplate.findAll(UserInfo.class, "user");
    }

    public UserInfo getOne(String name) {
        return mongoTemplate.findOne(new Query(Criteria.where("name").is(name)), UserInfo.class, "user");
    }

    public void add(UserInfo param) {
        mongoTemplate.save(param, "user");
    }

    public void modify(UserInfo param) {
        mongoTemplate.findAndModify(new Query(Criteria.where("id").is(param.getId())), new Update().set("name",param.getName()).set("address",param.getAddress()), UserInfo.class, "user");
    }

    public void delete(String id) {
        mongoTemplate.remove(new Query(Criteria.where("id").is(id)), UserInfo.class,"user");
    }

}

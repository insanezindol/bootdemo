package com.example.bootdemo.service;

import com.example.bootdemo.mapper.PostsMapper;
import com.example.bootdemo.vo.PostsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostsService {

    @Autowired
    private PostsMapper postsMapper;

    @Transactional(readOnly = true)
    public List<PostsInfo> getList() {
        return postsMapper.getList();
    }

    @Transactional(readOnly = true)
    public PostsInfo getOne(PostsInfo param) {
        return postsMapper.getOne(param);
    }

    @Transactional
    public int addPost(PostsInfo param) {
        return postsMapper.addPost(param);
    }

    @Transactional
    public int modifyPost(PostsInfo param) {
        return postsMapper.modifyPost(param);
    }

    @Transactional
    public int deletePost(PostsInfo param) {
        return postsMapper.deletePost(param);
    }
}

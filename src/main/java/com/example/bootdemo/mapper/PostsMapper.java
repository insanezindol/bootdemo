package com.example.bootdemo.mapper;

import com.example.bootdemo.vo.PostsInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsMapper {

    List<PostsInfo> getList();

    PostsInfo getOne(PostsInfo param);

    int addPost(PostsInfo param);

    int modifyPost(PostsInfo param);

    int deletePost(PostsInfo param);
}

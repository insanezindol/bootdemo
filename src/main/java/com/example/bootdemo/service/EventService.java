package com.example.bootdemo.service;

import com.example.bootdemo.mapper.EventVolumnMapper;
import com.example.bootdemo.mapper.PostsMapper;
import com.example.bootdemo.vo.EventVolumeInfo;
import com.example.bootdemo.vo.PostsInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class EventService {

    @Autowired
    private PostsMapper postsMapper;

    @Autowired
    private EventVolumnMapper eventVolumnMapper;

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

    public boolean isMMReflect(Integer uid, String startTime, String endTime, String eventKey) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Long nowDate = Long.parseLong(formatter.format(new Date()));

        switch (eventKey) {
            case "three_day_event":
                return Long.parseLong(startTime) <= nowDate && nowDate < Long.parseLong(endTime);
            default:
        }

        return false;
    }

    public boolean isWorkMM(List<String> timeList) {
        boolean isWork = true;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Long nowDate = Long.parseLong(formatter.format(new Date()));

        for (int i=0; i<timeList.size(); i++) {
            String[] timeStrArr = timeList.get(i).split(",");
            String startTime = timeStrArr[0];
            String endTime = timeStrArr[1];

            if (Long.parseLong(startTime) <= nowDate && nowDate < Long.parseLong(endTime) ) {
                isWork = false;
            }
        }

        return isWork;
    }

    @Transactional
    public EventVolumeInfo selectEventTradeBigAmount(int eventID, int uid) {
        return eventVolumnMapper.selectEventTradeBigAmount(eventID, uid);
    }

    @Transactional
    public int insertEventTradeBigAmount(EventVolumeInfo param) {
        return eventVolumnMapper.insertEventTradeBigAmount(param);
    }

    @Transactional
    public int updateEventTradeBigAmount(EventVolumeInfo param) {
        return eventVolumnMapper.updateEventTradeBigAmount(param);
    }

}

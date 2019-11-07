package com.example.bootdemo.mapper;

import com.example.bootdemo.vo.EventVolumeInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EventVolumnMapper {

    EventVolumeInfo selectEventTradeBigAmount(@Param("eventId") int eventId,
                                              @Param("uid") int uid);

    int insertEventTradeBigAmount(EventVolumeInfo param);

    int updateEventTradeBigAmount(EventVolumeInfo param);

}

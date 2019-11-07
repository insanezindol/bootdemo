package com.example.bootdemo.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class MainScheduler {

    // 초(0-59) 분(0-59) 시간(0-23) 일(1-31)　월(1-12)　요일(0-7)

    // cron 에 명시된 시간에 실행
    @Scheduled(cron = "0 0 * * * ?")
    public void cronJobSch() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String strDate = sdf.format(now);
        log.info("cron task - " + strDate);
    }

//    // 이전 수행이 종료된 시점부터 delay 후 실행
//    @Scheduled(fixedDelay = 1000)
//    public void scheduleFixedDelayTask() {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date now = new Date();
//        String strDate = sdf.format(now);
//        log.info("Fixed delay task - " + strDate);
//    }
//
//    // 이전 수행이 시작된 시점부터 rate 후 실행
//    @Scheduled(fixedRate = 5000)
//    public void scheduleFixedRateTask() {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date now = new Date();
//        String strDate = sdf.format(now);
//        log.info("Fixed rate task - " + strDate);
//    }

}

package com.example.bootdemo.controller;

import com.example.bootdemo.service.EventService;
import com.example.bootdemo.service.MailService;
import com.example.bootdemo.util.UniqueKeyUtil;
import com.example.bootdemo.vo.EventVolumeInfo;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/event")
@Slf4j
public class EventController {

    @Autowired
    EventService eventService;

    @Autowired
    MailService mailService;

    @GetMapping(value = "/logic")
    public JSONObject logic() {

        String[] symbolArr = {"KRW_NPLC", "KRW_TOX", "KRW_BTC", "KRW_ETH", "KRW_BCH", "KRW_ADA", "KRW_PLA", "KRW_LZE", "KRW_ADWD", "BTC_ETH", "DAI_BTC","DAI_ETH", "ETH_NPLC","TOX_BCH", "TOX_NPLC", "TOX_BTC"};

        for (int i = 0; i < symbolArr.length; i++) {
            String symbol = symbolArr[i];
            String prefixAsset = symbol.split("_")[0];
            String asset = symbol.split("_")[1];

            if (prefixAsset.equals("KRW")) {
                if (!asset.equals("NPLC") &&
                        !asset.equals("TOX") &&
                        !asset.equals("BTC") &&
                        !asset.equals("ETH") &&
                        !asset.equals("BCH") &&
                        !asset.equals("ADA") &&
                        !asset.equals("PLA")) {
                    log.info(symbol + " 리턴!");
                    continue;
                }
            }
            log.info(symbol + " 패스");
        }

        JSONObject data = new JSONObject();
        data.put("code", "100200");
        data.put("msg", "Success");
        data.put("data", null);
        return data;
    }

    @GetMapping(value = "/timeTest")
    public JSONObject timeTest(@RequestParam int uid) {
        log.info("uid : " + uid);

        boolean isMarketMaker = false;

        // timeStr 안에 선언된 시간에는 false, 그외 시간에는 true로 나옴
        if (uid == 14610) {
            List<String> timeStr = new ArrayList<>();
            timeStr.add("20190929030000,20190929040000");
            timeStr.add("20190930150000,20191001000000");
            isMarketMaker = eventService.isWorkMM(timeStr);
        } else if (uid == 14451) {
            List<String> timeStr = new ArrayList<>();
            timeStr.add("20190929030000,20190929040000");
            timeStr.add("20190930150000,20191001000000");
            isMarketMaker = eventService.isWorkMM(timeStr);
        } else if (uid == 16646) {
            List<String> timeStr = new ArrayList<>();
            timeStr.add("20190929010000,20190929050000");
            timeStr.add("20190930150000,20191001000000");
            isMarketMaker = eventService.isWorkMM(timeStr);
        } else if (uid == 18088) {
            List<String> timeStr = new ArrayList<>();
            timeStr.add("20190929030000,20190929040000");
            timeStr.add("20190930150000,20191001000000");
            isMarketMaker = eventService.isWorkMM(timeStr);
        } else if (uid == 15026) {
            // 24시간 내내 마켓메이커 투입
        } else {
            // 이외에는 마켓메이커인지 체크 후 처리.
            // isMarketMaker = userMarketMakerMapper.exists(uid);
        }

        log.info("isMarketMaker : " + isMarketMaker);

        JSONObject data = new JSONObject();
        data.put("code", "100200");
        data.put("msg", "Success");
        data.put("data", isMarketMaker);
        return data;
    }

    @GetMapping(value = "/updateTest")
    public JSONObject updateTest(@RequestParam int eventID, @RequestParam int uid, @RequestParam BigDecimal tradeAmount) {
        EventVolumeInfo info = eventService.selectEventTradeBigAmount(eventID, uid);

        if (info == null) {
            log.info("insert");
            int userGroup = 0;

            EventVolumeInfo param = new EventVolumeInfo();
            param.setEvent_id(eventID);
            param.setUid(uid);
            param.setUser_group(userGroup);
            param.setTrade_amount(tradeAmount);
            int resultCnt = eventService.insertEventTradeBigAmount(param);
            log.info("[INS] resultCnt : " + resultCnt);
        } else {
            log.info("update");
            log.info(""+info.getTrade_amount().compareTo(tradeAmount));
            if(info.getTrade_amount().compareTo(tradeAmount) < 0) {
                // 0이면 같다 ( 아무런 액션 하지 않음 )
                // 1이면 DB값이 더 크다 ( 아무런 액션 하지 않음 )
                // -1이면 현재거래한 금액이 더 크다 ( DB값을 업데이트 한다 )
                EventVolumeInfo param = new EventVolumeInfo();
                param.setEvent_id(eventID);
                param.setUid(uid);
                param.setTrade_amount(tradeAmount);
                int resultCnt = eventService.updateEventTradeBigAmount(param);
                log.info("[UPD] resultCnt : " + resultCnt);
            } else {
                log.info("NO ACTION");
            }
        }

        JSONObject data = new JSONObject();
        data.put("code", "100200");
        data.put("msg", "Success");
        data.put("data", info);
        return data;
    }

    @GetMapping(value = "/prizeMoney")
    public JSONObject prizeMoney(@RequestParam String type) {

        // 랜덤 거래 지급금
        if(type.equals("first")){
            int n1 = 30;
            int n2 = 50;

            int total = 0;
            for (int i=0; i<10; i++) {
                int randNum = (int) (Math.random() * (n2 - n1 + 1)) + n1;
                randNum *= 100;
                log.info("" + randNum);
                total+=randNum;
            }
            log.info("avg :" + total/10);
        } else if (type.equals("exist")) {
            int n1 = 3;
            int n2 = 5;

            for (int i=0; i<10; i++) {
                int randNum = (int) (Math.random() * (n2 - n1 + 1)) + n1;
                log.info("" + randNum);
            }
        }

        // 고유값 생성값 1
        for(int i=0; i<5; i++) {
            long time = System.nanoTime();
            log.info(time + " -> " + UniqueKeyUtil.longToBase64(time));
        }

        // 고유값 생성값 2
        for(int i=0; i<5; i++) {
            String uuid = UUID.randomUUID().toString().replace("-", "");
            log.info(uuid);
        }

        // 고유값 생성값 3
        Random rnd = new Random();
        StringBuffer buf = new StringBuffer();
        for(int i=0; i<8; i++) {
            int typeInt = rnd.nextInt(3) % 3;
            if (typeInt == 0) {
                buf.append((char)((int)(rnd.nextInt(26))+65));
            } else if (typeInt == 1) {
                buf.append((char)((int)(rnd.nextInt(26))+97));
            } else {
                buf.append((rnd.nextInt(10)));
            }
        }
        log.info(buf.toString());

        JSONObject data = new JSONObject();
        data.put("code", "100200");
        data.put("msg", "Success");
        data.put("data", null);
        return data;
    }

    @GetMapping(value = "/proportion")
    public JSONObject proportion(@RequestParam BigDecimal totalAmount) {

        BigDecimal stage02 = new BigDecimal("100000000");   // 1억
        BigDecimal stage03 = new BigDecimal("500000000");   // 5억
        BigDecimal stage04 = new BigDecimal("1000000000");  // 10억
        BigDecimal stage05 = new BigDecimal("2000000000");  // 20억
        BigDecimal stage06 = new BigDecimal("3000000000");  // 30억
        BigDecimal stage07 = new BigDecimal("4000000000");  // 40억
        BigDecimal stage08 = new BigDecimal("5000000000");  // 50억
        BigDecimal stage09 = new BigDecimal("6000000000");  // 60억
        BigDecimal stage10 = new BigDecimal("7000000000");  // 70억
        BigDecimal stage11 = new BigDecimal("8000000000");  // 80억
        BigDecimal stage12 = new BigDecimal("10000000000"); // 100억

        int currentStage = 1;
        BigDecimal currentTotalPrizeMoney = BigDecimal.ZERO;
        BigDecimal prizeMoney1st = BigDecimal.ZERO;
        BigDecimal prizeMoney2st = BigDecimal.ZERO;
        BigDecimal prizeMoney3st = BigDecimal.ZERO;
        BigDecimal prizeMoney4st = BigDecimal.ZERO;
        BigDecimal prizeMoney5st = BigDecimal.ZERO;
        BigDecimal startPrizeMoney = BigDecimal.ZERO;
        BigDecimal endPrizeMoney = BigDecimal.ZERO;

        if(totalAmount.compareTo(stage02) < 0){
            currentStage = 1;
            currentTotalPrizeMoney = new BigDecimal("100000");
            endPrizeMoney = stage02;
        } else if(totalAmount.compareTo(stage02) > -1 && totalAmount.compareTo(stage03) < 0) {
            currentStage = 2;
            currentTotalPrizeMoney = new BigDecimal("250000");
            startPrizeMoney = stage02;
            endPrizeMoney = stage03;
        } else if(totalAmount.compareTo(stage03) > -1 && totalAmount.compareTo(stage04) < 0) {
            currentStage = 3;
            currentTotalPrizeMoney = new BigDecimal("1350000");
            startPrizeMoney = stage03;
            endPrizeMoney = stage04;
        } else if(totalAmount.compareTo(stage04) > -1 && totalAmount.compareTo(stage05) < 0) {
            currentStage = 4;
            currentTotalPrizeMoney = new BigDecimal("2700000");
            startPrizeMoney = stage04;
            endPrizeMoney = stage05;
        } else if(totalAmount.compareTo(stage05) > -1 && totalAmount.compareTo(stage06) < 0) {
            currentStage = 5;
            currentTotalPrizeMoney = new BigDecimal("5400000");
            startPrizeMoney = stage05;
            endPrizeMoney = stage06;
        } else if(totalAmount.compareTo(stage06) > -1 && totalAmount.compareTo(stage07) < 0) {
            currentStage = 6;
            currentTotalPrizeMoney = new BigDecimal("8100000");
            startPrizeMoney = stage06;
            endPrizeMoney = stage07;
        } else if(totalAmount.compareTo(stage07) > -1 && totalAmount.compareTo(stage08) < 0) {
            currentStage = 7;
            currentTotalPrizeMoney = new BigDecimal("10800000");
            startPrizeMoney = stage07;
            endPrizeMoney = stage08;
        } else if(totalAmount.compareTo(stage08) > -1 && totalAmount.compareTo(stage09) < 0) {
            currentStage = 8;
            currentTotalPrizeMoney = new BigDecimal("13500000");
            startPrizeMoney = stage08;
            endPrizeMoney = stage09;
        } else if(totalAmount.compareTo(stage09) > -1 && totalAmount.compareTo(stage10) < 0) {
            currentStage = 9;
            currentTotalPrizeMoney = new BigDecimal("16200000");
            startPrizeMoney = stage09;
            endPrizeMoney = stage10;
        } else if(totalAmount.compareTo(stage10) > -1 && totalAmount.compareTo(stage11) < 0) {
            currentStage = 10;
            currentTotalPrizeMoney = new BigDecimal("18900000");
            startPrizeMoney = stage10;
            endPrizeMoney = stage11;
        } else if(totalAmount.compareTo(stage11) > -1 && totalAmount.compareTo(stage12) < 0) {
            currentStage = 11;
            currentTotalPrizeMoney = new BigDecimal("21600000");
            startPrizeMoney = stage11;
            endPrizeMoney = stage12;
        } else if(totalAmount.compareTo(stage12) > -1) {
            currentStage = 12;
            currentTotalPrizeMoney = new BigDecimal("27000000");
            startPrizeMoney = stage11;
            endPrizeMoney = stage12;
        }

        prizeMoney1st = currentTotalPrizeMoney.multiply(new BigDecimal("0.450")).setScale(0, RoundingMode.FLOOR);
        prizeMoney2st = currentTotalPrizeMoney.multiply(new BigDecimal("0.250")).setScale(0, RoundingMode.FLOOR);
        prizeMoney3st = currentTotalPrizeMoney.multiply(new BigDecimal("0.150")).setScale(0, RoundingMode.FLOOR);
        prizeMoney4st = currentTotalPrizeMoney.multiply(new BigDecimal("0.10")).setScale(0, RoundingMode.FLOOR);
        prizeMoney5st = currentTotalPrizeMoney.multiply(new BigDecimal("0.050")).setScale(0, RoundingMode.FLOOR);

        // logging
        log.info("currentStage : " + currentStage);
        log.info("totalAmount : " + totalAmount);
        log.info("currentTotalPrizeMoney : " + currentTotalPrizeMoney);
        log.info("startPrizeMoney : " + startPrizeMoney);
        log.info("endPrizeMoney : " + endPrizeMoney);
        log.info("prizeMoney1st : " + prizeMoney1st);
        log.info("prizeMoney2st : " + prizeMoney2st);
        log.info("prizeMoney3st : " + prizeMoney3st);
        log.info("prizeMoney4st : " + prizeMoney4st);
        log.info("prizeMoney5st : " + prizeMoney5st);

        JSONObject proportion = new JSONObject();
        proportion.put("currentStage", currentStage);
        proportion.put("totalAmount", totalAmount);
        proportion.put("currentTotalPrizeMoney", currentTotalPrizeMoney);
        proportion.put("startPrizeMoney", startPrizeMoney);
        proportion.put("endPrizeMoney", endPrizeMoney);
        proportion.put("prizeMoney1st", prizeMoney1st);
        proportion.put("prizeMoney2st", prizeMoney2st);
        proportion.put("prizeMoney3st", prizeMoney3st);
        proportion.put("prizeMoney4st", prizeMoney4st);
        proportion.put("prizeMoney5st", prizeMoney5st);

        JSONObject data = new JSONObject();
        data.put("code", "100200");
        data.put("msg", "Success");
        data.put("data", proportion);
        return data;
    }

    @GetMapping(value = "/sleep")
    public JSONObject sleep(@RequestParam long millis) {

        for (int i=0; i<10; i++) {
            try {
                log.info("cnt : " + i);
                Thread.sleep(millis);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        JSONObject data = new JSONObject();
        data.put("code", "100200");
        data.put("msg", "Success");
        data.put("data", null);
        return data;
    }

    @GetMapping(value = "/mail")
    public JSONObject mail() {
        String to = "jhlee90@metaps-plus.com";
        String subject = "test mail subject";
        String message = "test mail contents";

        mailService.sendAmazonMail(to, subject, message);

        JSONObject data = new JSONObject();
        data.put("code", "100200");
        data.put("msg", "Success");
        data.put("data", null);
        return data;
    }

}

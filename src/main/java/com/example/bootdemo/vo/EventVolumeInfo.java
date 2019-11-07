package com.example.bootdemo.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EventVolumeInfo {
    private int event_id;
    private int uid;
    private int user_group;
    private BigDecimal trade_amount;
    private String create_date;
    private String update_date;
}

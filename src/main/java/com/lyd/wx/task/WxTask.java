package com.lyd.wx.task;

import com.alibaba.fastjson.JSON;
import com.lyd.wx.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import java.text.ParseException;


/**
 * 描述:
 *
 * @author liyadong
 * @create 2022-08-22-1:06-周一
 */
@Component
public class WxTask {
    @Autowired
    MyWx wx;
    @Autowired
    GetData getData;
    @Autowired
    Send send;

    //每天早上8点发送天气数据
    @Scheduled(cron="0 0 8 * * ?")
    public void weatherWx() throws ParseException {
        //获取天气
        Weather weather = getData.weather();
        String token = getData.getToken();
        //给设定的用户循环发送
        for(String uid:wx.getUsers()){
            send.send1(token,weather,uid);
        }
    }

    //每天早上9点发送时间数据
    @Scheduled(cron="0 0 9 * * ?")
    public void dayWx() throws ParseException {
        //获取天气
        Weather weather = getData.weather();
        String token = getData.getToken();
        //给设定的用户循环发送
        for(String uid:wx.getUsers()){
            send.send2(token,uid);
        }
    }

    //在这个时间点提醒喝水
    @Scheduled(cron="0 0 10,13,14,15,16,17,18,19,20,21,22,23 * * ?")
    public void drinkwaterWx(){
        //获取天气
        Weather weather = getData.weather();
        String token = getData.getToken();
        //给设定的用户循环发送
        for(String uid:wx.getUsers()){
            send.send3(token, uid,
                    "天干物燥ヾ(≧▽≦*)o记得喝水水哦", "#ffb199",
                    null,null,null,null);
        }
    }

    //每天15点半和18点半提醒学习
    @Scheduled(cron="0 30 15,18 * * ?")
    public void studyWx(){
        //获取天气
        Weather weather = getData.weather();
        String token = getData.getToken();
        //给设定的用户循环发送
        for(String uid:wx.getUsers()){
            send.send3(token, uid,
                    "今天学习了没有呀ヾ(￣ー￣)X(^▽^)ゞ", "#ff758c",
                    null,null,null,null);
        }
    }







}

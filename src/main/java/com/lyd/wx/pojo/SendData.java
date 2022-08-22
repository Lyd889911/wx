package com.lyd.wx.pojo;

import lombok.Data;

/**
 * 描述:
 *
 * @author liyadong
 * @create 2022-08-22-2:31-周一
 * */
@Data
public class SendData {
    //城市
    private Item city;
    //日期
    private Item date;
    //星期几
    private Item week;
    //天气，晴，雨等
    private Item wea;
    //最高气温
    private Item tem1;
    //最低气温
    private Item tem2;
    //风向
    private Item win;
    //风级
    private Item win_speed;
    //风速
    private Item win_meter;
    //湿度
    private Item humidity;
    //能见度
    private Item visibility;
    //气压
    private Item pressure;
    //空气质量
    private Item air;
    //pm2.5值
    private Item air_pm25;
    //空气等级
    private Item air_level;
    //提示
    private Item air_tips;
    //英文每日一句
    private Item content;
    //中文每日一句
    private Item note;
    //附加文本，自定义内容
    private Item txt1;
    private Item txt2;
    private Item txt3;
    private Item txt4;
    private Item txt5;
    private Item txt6;
    private Item txt7;
    private Item txt8;
    private Item txt9;
    //认识多少天
    private Item day1;
    //在一起多少天
    private Item day2;
    //离我生日还有多少天
    private Item day3;
    //离她生日还有多少天
    private Item day4;
    //我出生多少天
    private Item day5;
    //宝贝出生多少天
    private Item day6;
}

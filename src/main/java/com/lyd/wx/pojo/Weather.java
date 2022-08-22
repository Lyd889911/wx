package com.lyd.wx.pojo;

import lombok.Data;

/**
 * 描述:
 *
 * @author liyadong
 * @create 2022-08-22-1:58-周一
 */
@Data
public class Weather {
    //城市
    private String city;
    //日期
    private String date;
    //星期几
    private String week;
    //天气，晴，雨等
    private String wea;
    //最高气温
    private String tem1;
    //最低气温
    private String tem2;
    //风向
    private String win;
    //风级
    private String win_speed;
    //风速
    private String win_meter;
    //湿度
    private String humidity;
    //能见度
    private String visibility;
    //气压
    private String pressure;
    //空气质量
    private String air;
    //pm2.5值
    private String air_pm25;
    //空气等级
    private String air_level;
    //提示
    private String air_tips;


}

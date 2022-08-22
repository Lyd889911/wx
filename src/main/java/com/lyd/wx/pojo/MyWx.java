package com.lyd.wx.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 描述:
 *
 * @author liyadong
 * @create 2022-08-22-13:06-周一
 */
@Component
@ConfigurationProperties(prefix ="mywx")
@Data
public class MyWx {
    private String appID;
    private String appsecret;
    private String tempateid1;
    private String tempateid2;
    private String tempateid3;
    private String cityid;
    private String day1;
    private String day2;
    private String day3;
    private String day4;
    private List<String> users;
}

package com.lyd.wx.pojo;

import lombok.Data;

/**
 * 描述:
 *
 * @author liyadong
 * @create 2022-08-22-1:17-周一
 */
@Data
public class AccessToken {
    private String access_token;
    private Integer expires_in;
}

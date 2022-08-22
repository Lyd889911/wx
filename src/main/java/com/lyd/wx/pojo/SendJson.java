package com.lyd.wx.pojo;

import lombok.Data;

/**
 * 描述:
 *
 * @author liyadong
 * @create 2022-08-22-2:33-周一
 */
@Data
public class SendJson {
    private String touser;
    private String template_id;
    private String topcolor;
    private SendData data;
}

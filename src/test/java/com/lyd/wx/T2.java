package com.lyd.wx;

import com.alibaba.fastjson.JSON;
import com.lyd.wx.pojo.*;
import com.lyd.wx.uitls.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;

/**
 * 描述:
 *
 * @author liyadong
 * @create 2022-08-22-15:52-周一
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class T2 {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    MyWx wx;
    @Test
    public void everydayWx() throws ParseException {
        String url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+wx.getAppID()+"&secret="+wx.getAppsecret();
        AccessToken accessToken = restTemplate.getForObject(url, AccessToken.class);
        System.out.println(accessToken);
        //获取每日一句
        Content content = everydaySentence();
        //获取天气
        Weather weather = weather();
        //给设定的用户循环发送
//        for(String uid:wx.getUsers()){
//
//        }
        //send1(accessToken.getAccess_token(),weather,"ohdhc5qNutEtYupmzRIs8JFnA9fU");
        //send2(accessToken.getAccess_token(),"ohdhc5qNutEtYupmzRIs8JFnA9fU");
        send3(accessToken.getAccess_token(),"ohdhcz8JFnA9fU","有没有在学习啊，查岗","#74ebd5",null,null,null,null);
    }
    /**
     * 每日一句
     */
    public Content everydaySentence(){
        String url = "http://open.iciba.com/dsapi/";
        String forObject = restTemplate.getForObject(url, String.class);
        Content content = JSON.parseObject(forObject, Content.class);
        System.out.println(content);
        return content;
    }

    /**
     * 天气详情
     */
    public Weather weather(){
        String url="https://v0.yiketianqi.com/api?unescape=1&version=v61&appid=43656176&appsecret=I42og6Lm&ext=&cityid="+wx.getCityid()+"&city=";
        String forObject = restTemplate.getForObject(url, String.class);
        Weather weather = JSON.parseObject(forObject, Weather.class);
        System.out.println(weather);
        return weather;
    }
    /**
     * 发送天气数据
     * @param token
     * @param w
     * @throws ParseException
     */
    public void send1(String token,Weather w,String userid) throws ParseException {
        String url="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+token;

        String c1="#fdcbf1";
        SendJson sendJson = new SendJson();
        sendJson.setTouser(userid);
        sendJson.setTemplate_id(wx.getTempateid1());
        sendJson.setTopcolor(c1);
        //数据
        SendData sendData = new SendData();
        //城市和日期
        sendData.setCity(new Item(w.getCity(),"#f093fb"));
        sendData.setDate(new Item(w.getDate(),"#f093fb"));
        sendData.setWeek(new Item(w.getWeek(),"#f093fb"));
        //关于空气
        sendData.setAir(new Item(w.getAir(),"#0acffe"));
        sendData.setAir_level(new Item(w.getAir_level(),"#0acffe"));
        sendData.setAir_pm25(new Item(w.getAir_pm25(),"#0acffe"));
        sendData.setHumidity(new Item(w.getHumidity(),"#0acffe"));
        sendData.setAir_tips(new Item(w.getAir_tips(),"#0acffe"));
        sendData.setPressure(new Item(w.getPressure(),"#0acffe"));
        sendData.setVisibility(new Item(w.getVisibility(),"#0acffe"));
        //关于温度天气
        sendData.setTem1(new Item(w.getTem1(),"#f43b47"));
        sendData.setTem2(new Item(w.getTem2(),"#f43b47"));
        sendData.setWea(new Item(w.getWea(),"#f43b47"));
        //关于风
        sendData.setWin_meter(new Item(w.getWin_meter(),"#88d3ce"));
        sendData.setWin_speed(new Item(w.getWin_speed(),"#88d3ce"));
        sendData.setWin(new Item(w.getWin(),"#88d3ce"));
        //附加数据

        sendData.setTxt1(new Item("亲爱的乖乖宝贝，早上好！ (๑•̀ㅂ•́)و✧记得按时吃早饭午饭晚饭。今天也要开心哦ヽ(✿ﾟ▽ﾟ)ノ","#ff7eb3"));
        sendData.setTxt2(new Item("下面由我来给你播报一下今天的天气状况╰(*°▽°*)╯","#f09819"));
        //发送请求
        sendJson.setData(sendData);
        String s = restTemplate.postForObject(url, sendJson, String.class);
        System.out.println(s);
    }

    /**
     * 发送纪念日数据
     * @param token
     */
    public void send2(String token,String userid) throws ParseException {
        String url="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+token;
        String c1="#fdcbf1";
        SendJson sendJson = new SendJson();
        sendJson.setTouser(userid);
        sendJson.setTemplate_id(wx.getTempateid2());
        sendJson.setTopcolor(c1);

        SendData sd = new SendData();
        //距离认识多少天
        sd.setDay1(new Item(DateUtils.dayForLastDay(wx.getDay1()),"#fee140"));
        //距离在一起多少天
        sd.setDay2(new Item(DateUtils.dayForLastDay(wx.getDay2()),"#fee140"));
        //距离我生日多少天
        sd.setDay3(new Item(DateUtils.dayToBirthday(wx.getDay3()),"#fee140"));
        //距离宝贝生日多少天
        sd.setDay4(new Item(DateUtils.dayToBirthday(wx.getDay4()),"#fee140"));
        //距离我出生多少天
        sd.setDay5(new Item(DateUtils.dayForLastDay(wx.getDay3()),"#fee140"));
        //距离宝贝出生多少天
        sd.setDay6(new Item(DateUtils.dayForLastDay(wx.getDay4()),"#fee140"));
        //附加文本数据
        sd.setTxt1(new Item("宝贝，今天也很爱你哦(*/ω＼*)","#cd9cf2"));
        sd.setTxt2(new Item("还有很长的日子，要一直陪你走下去 *′∀`)′∀`)*′∀`)*′∀`)","#cd9cf2"));
        sendJson.setData(sd);
        String s = restTemplate.postForObject(url, sendJson, String.class);
        System.out.println(s);
    }
    /**
     * 事件提醒
     * 可以设置三句话，不同颜色，t文本，c颜色
     */
    public void send3(String token,String userid,String t1,String c1,String t2,String c2,String t3,String c3){
        String url="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+token;
        String c="#fdcbf1";
        SendJson sendJson = new SendJson();
        sendJson.setTouser(userid);
        sendJson.setTemplate_id(wx.getTempateid3());
        sendJson.setTopcolor(c);
        SendData sd = new SendData();
        sd.setTxt1(new Item(t1,c1));
        sd.setTxt2(new Item(t2,c2));
        sd.setTxt3(new Item(t3,c3));
        sendJson.setData(sd);
        String s = restTemplate.postForObject(url, sendJson, String.class);
        System.out.println(s);
    }
}

package com.lyd.wx;

import com.alibaba.fastjson.JSON;
import com.lyd.wx.pojo.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * 描述:
 *
 * @author liyadong
 * @create 2022-08-22-1:09-周一
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class T {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    MyWx wx;
    @Test
    public void  t1() throws ParseException {
        String appID="wx35c71";
        String appsecret="b14";
        String url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appID+"&secret="+appsecret;
        AccessToken accessToken = restTemplate.getForObject(url, AccessToken.class);
        System.out.println(accessToken);
        Content content = everydaySentence();
        Weather weather = weather();
        send(accessToken.getAccess_token(),weather,content);
    }
    @Test
    public void t2(){
        System.out.println(wx.getAppID());
        System.out.println(wx.getAppsecret());
        System.out.println(wx.getUsers());
    }
    @Test
    public void dayToBirthday() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birth = sdf.parse("2000-04-25");

        Calendar cToday = Calendar.getInstance(); // 存今天
        Calendar cBirth = Calendar.getInstance(); // 存生日
        cBirth.setTime(birth); // 设置生日
        cBirth.set(Calendar.YEAR, cToday.get(Calendar.YEAR)); // 修改为本年
        int days;
        if (cBirth.get(Calendar.DAY_OF_YEAR) < cToday.get(Calendar.DAY_OF_YEAR)) {
            // 生日已经过了，要算明年的了
            days = cToday.getActualMaximum(Calendar.DAY_OF_YEAR) - cToday.get(Calendar.DAY_OF_YEAR);
            cBirth.set(Calendar.YEAR, cToday.get(Calendar.YEAR)+1); // 修改为明年
            days += cBirth.get(Calendar.DAY_OF_YEAR);
        } else {
            // 生日还没过
            days = cBirth.get(Calendar.DAY_OF_YEAR) - cToday.get(Calendar.DAY_OF_YEAR);
        }
        // 输出结果
        if (days == 0) {
            System.out.println("今天生日");
        } else {
            System.out.println("距离生日还有：" + days + "天");
        }
        //return days;
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
        String cityid="101041700";
        String url="https://v0.yiketianqi.com/api?unescape=1&version=v61&appid=43656176&appsecret=I42og6Lm&ext=&cityid="+cityid+"&city=%22";
        String forObject = restTemplate.getForObject(url, String.class);
        Weather weather = JSON.parseObject(forObject, Weather.class);
        System.out.println(weather);
        return weather;
    }
    public void send(String token,Weather w,Content c) throws ParseException {
        String url="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+token;
        String userid="ohdhc5qNutEtYupmzRIs8JFnA9fU";
        String tempateid="jO35TDx6D9rZvlqwtAj57ptaMiEvvao8sipyhZEKaiM";
        String c1="#fdcbf1";
        SendJson sendJson = new SendJson();
        sendJson.setTouser(userid);
        sendJson.setTemplate_id(tempateid);
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
        //关于每日一句
        sendData.setNote(new Item(c.getNote(),"#50cc7f"));
        sendData.setContent(new Item(c.getContent(),"#faaca8"));
        //附加数据
        //计算时间
        long t = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long t1 = sdf.parse("2021-11-07").getTime();
        long t2 = sdf.parse("2021-11-13").getTime();
        int d1 = (int)((t-t1)/ 1000/60/60/24)+1;
        int d2 = (int)((t-t2)/ 1000/60/60/24)+1;


        sendData.setTxt1(new Item("亲爱的乖乖宝贝，早上好！ (๑•̀ㅂ•́)و✧记得按时吃早饭午饭晚饭。今天也要开心哦ヽ(✿ﾟ▽ﾟ)ノ","#ff7eb3"));
        sendData.setTxt2(new Item(String.valueOf(d1),"#ff0844"));
        sendData.setTxt3(new Item(String.valueOf(d2),"#ff0844"));
        sendData.setTxt4(new Item("下面由我来给你播报一下今天的天气状况╰(*°▽°*)╯","#f09819"));
        sendData.setTxt5(new Item("最后给你来个每日一句，加油哦づ￣3￣）づ╭❤～","#6713d2"));
        //发送请求
        sendJson.setData(sendData);
        //String s1 = JSON.toJSONString(sendJson);
        String s = restTemplate.postForObject(url, sendJson, String.class);
        System.out.println(s);
    }
}

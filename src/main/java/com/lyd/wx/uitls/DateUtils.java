package com.lyd.wx.uitls;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 描述:
 *
 * @author liyadong
 * @create 2022-08-22-15:12-周一
 */
public class DateUtils {
    /**
     * 返回生日还有多久，天
     * @param birthday
     * @return
     * @throws ParseException
     */
    public static String dayToBirthday(String birthday) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birth = sdf.parse(birthday);

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
        return String.valueOf(days);
    }

    /**
     * 当前离某个日子过去多少天了
     * @param lastday
     * @return
     */
    public static String dayForLastDay(String lastday) throws ParseException {
        long t = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long t1 = sdf.parse(lastday).getTime();
        int d1 = (int)((t-t1)/ 1000/60/60/24)+1;
        return String.valueOf(d1);
    }
}

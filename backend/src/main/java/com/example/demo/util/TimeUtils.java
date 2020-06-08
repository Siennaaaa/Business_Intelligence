package com.example.demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
    //获取时间
    public static String getCurrentTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }
}

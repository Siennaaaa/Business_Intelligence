package com.example.demo.util;


public class ParamUtils {
    //把参数转化为字符串
    public static String paramsToString(Object... params){
        StringBuilder stringBuilder = new StringBuilder();
        for (Object param : params){
            stringBuilder.append(String.valueOf(param)).append("-");
        }
        System.out.println();
        return stringBuilder.toString().substring(0, stringBuilder.length()-1);
    }

    //检查步数是否超过最值
    public static int checkStep(int step){
        if (step <= ConfigUtils.MinStep)
            return ConfigUtils.MinStep;
        if (step >= ConfigUtils.MaxStep)
            return ConfigUtils.MaxStep;
        return step;
    }

    //检查限制
    public static int checkLimit(int limit){
        if (limit <= ConfigUtils.MinLimit)
            return ConfigUtils.MinLimit;
        if (limit >= ConfigUtils.MaxLimit)
            return ConfigUtils.MaxLimit;
        return limit;
    }
}

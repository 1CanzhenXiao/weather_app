package com.example.weather_app.juhe;

public class URLUtils {

    public static final String KEY = "778c3d6e4d1a04f3b88eec5c842a58cf";
    public static String temp_url = "http://apis.juhe.cn/simpleWeather/query";
    public static String index_url = "http://apis.juhe.cn/simpleWeather/life";

    //查询天气
    public static String getTemp_url(String city){
        String url = temp_url+"?city="+city+"&key="+KEY;
        return url;
    }

    //查询天气指数
    public static String getIndex_url(String city){
        String url = index_url+"?city="+city+"&key="+KEY;
        return url;
    }
}
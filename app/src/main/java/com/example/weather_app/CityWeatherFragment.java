package com.example.weather_app;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.example.weather_app.base.BaseFragment;
import com.example.weather_app.db.DBManager;
import com.example.weather_app.juhe.HttpUtils;
import com.example.weather_app.juhe.JHIndexBean;
import com.example.weather_app.juhe.JHTempBean;
import com.example.weather_app.juhe.URLUtils;
import com.google.gson.Gson;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
/**
 * 主页面当中ViewPager所嵌套的Fragment
 */
public class CityWeatherFragment extends BaseFragment implements View.OnClickListener{
    TextView tempTv,cityTv,conditionTv,windTv,tempRangeTv,dateTv,clothIndexTv,carIndexTv,coldIndexTv,sportIndexTv,raysIndexTv,airIndexTv;
    ImageView dayIv;
    LinearLayout futureLayout;
    ScrollView outLayout;
    JHIndexBean.ResultBean.LifeBean lifeBean;    //指数信息
    String city;
    private SharedPreferences pref;
    private int bgNum;

    private void initView(View view) {
        //用于初始化控件操作
        tempTv = view.findViewById(R.id.frag_tv_currenttemp);   //现在温度
        cityTv = view.findViewById(R.id.frag_tv_city);      //城市
        conditionTv = view.findViewById(R.id.frag_tv_condition);    //天气状态
        windTv = view.findViewById(R.id.frag_tv_wind);      //风向
        tempRangeTv = view.findViewById(R.id.frag_tv_temprange);    //今天温度范围
        dateTv = view.findViewById(R.id.frag_tv_date);      //今天的日期
        clothIndexTv = view.findViewById(R.id.frag_index_tv_dress);     //穿衣建议
        carIndexTv = view.findViewById(R.id.frag_index_tv_washcar);     //洗车建议
        coldIndexTv = view.findViewById(R.id.frag_index_tv_cold);   //感冒
        sportIndexTv = view.findViewById(R.id.frag_index_tv_sport);    //运动建议
        raysIndexTv = view.findViewById(R.id.frag_index_tv_rays);   //紫外线强度
        airIndexTv = view.findViewById(R.id.frag_index_tv_air);    //舒适度
        dayIv = view.findViewById(R.id.frag_iv_today);  //天气图标
        futureLayout = view.findViewById(R.id.frag_center_layout);  //未来三天数据
        outLayout = view.findViewById(R.id.out_layout);
        //设置点击事件的监听
        clothIndexTv.setOnClickListener(this);
        carIndexTv.setOnClickListener(this);
        coldIndexTv.setOnClickListener(this);
        sportIndexTv.setOnClickListener(this);
        raysIndexTv.setOnClickListener(this);
        airIndexTv.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_city_weather, container, false);
        initView(view);
        //可以通过activity传值获取到当前fragment加载的是那个地方的天气情况
        Bundle bundle = getArguments();
        city = bundle.getString("city");
        String tempUrl = URLUtils.getTemp_url(city);  //请求并获得信息

        //调用父类获取数据的方法
        loadData(tempUrl);

        // 获取指数信息的网址
        String index_url = URLUtils.getIndex_url(city);
        loadIndexData(index_url);
        return view;
    }

     /* 网络获取指数信息*/
    private void loadIndexData(final String index_url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String json = HttpUtils.getJsonContent(index_url);
                JHIndexBean jhIndexBean = new Gson().fromJson(json, JHIndexBean.class);
                if (jhIndexBean.getResult()!=null) {
                    lifeBean = jhIndexBean.getResult().getLife();
                }
            }
        }).start();
    }

    @Override
    public void onSuccess(String result) {
        //解析并展示数据
         parseShowData(result);
        //更新数据
        int i = DBManager.updateInfoByCity(city, result);
        if (i<=0) {
        //更新数据库失败，说明没有这条城市信息，增加这个城市记录
            DBManager.addCityInfo(city,result);
        }
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        //数据库当中查找上一次信息显示在Fragment当中
        String s = DBManager.queryInfoByCity(city);
        if (!TextUtils.isEmpty(s)) parseShowData(s);
    }

    private void parseShowData(String result) {
        //使用gson解析数据
        JHTempBean jhTempBean = new Gson().fromJson(result, JHTempBean.class);
        JHTempBean.ResultBean jhResult = jhTempBean.getResult();
        //设置TextView
        dateTv.setText(jhResult.getFuture().get(0).getDate());
        cityTv.setText(jhResult.getCity());
        //获取今日天气情况
        JHTempBean.ResultBean.FutureBean jhTodayFuture = jhResult.getFuture().get(0);
        JHTempBean.ResultBean.RealtimeBean jhRealtime = jhResult.getRealtime();
        windTv.setText(jhRealtime.getDirect() + jhRealtime.getPower());
        tempRangeTv.setText(jhTodayFuture.getTemperature());
        String condition = jhRealtime.getInfo();
        conditionTv.setText(condition);
        tempTv.setText(jhRealtime.getTemperature());

        //根据天气阴晴设置天气图像
        switch (condition) {
            case "晴":
                dayIv.setImageResource(R.mipmap.w0);
                break;
            case "多云":
                dayIv.setImageResource(R.mipmap.w1);
                break;
            case "阴":
                dayIv.setImageResource(R.mipmap.w2);
                break;
            case "阵雨":
                dayIv.setImageResource(R.mipmap.w3);
                break;
            case "雷阵雨":
                dayIv.setImageResource(R.mipmap.w4);
                break;
            case "雨夹雪":
                dayIv.setImageResource(R.mipmap.w6);
                break;
            case "小雨":
                dayIv.setImageResource(R.mipmap.w7);
                break;
            case "中雨":
                dayIv.setImageResource(R.mipmap.w8);
                break;
            case "大雨":
                dayIv.setImageResource(R.mipmap.w9);
                break;
            case "雾":
                dayIv.setImageResource(R.mipmap.w10);
                break;
            case "沙尘暴":
                dayIv.setImageResource(R.mipmap.w11);
                break;
            case "霾":
                dayIv.setImageResource(R.mipmap.w12);
                break;
        }

        //获取未来三天的天气情况，加载到layout当中
        List<JHTempBean.ResultBean.FutureBean> futureList = jhResult.getFuture();
        futureList.remove(0);
        for (int i = 0; i < futureList.size()-1; i++) {
            View itemView = LayoutInflater.from(getActivity()).inflate(R.layout.item_main_center, null);
            itemView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            futureLayout.addView(itemView);
            TextView idateTv = itemView.findViewById(R.id.item_center_tv_date);
            TextView iconTv = itemView.findViewById(R.id.item_center_tv_con);
            TextView windTv = itemView.findViewById(R.id.item_center_tv_wind);
            TextView itemprangeTv = itemView.findViewById(R.id.item_center_tv_temp);
            //获取对应的位置的天气情况
            JHTempBean.ResultBean.FutureBean dataBean = futureList.get(i);
            idateTv.setText(dataBean.getDate());
            iconTv.setText(dataBean.getWeather());
            itemprangeTv.setText(dataBean.getTemperature());
            windTv.setText(dataBean.getDirect());
            idateTv.setTextColor(0xFFFFFFFF);
            iconTv.setTextColor(0xFFFFFFFF);
            itemprangeTv.setTextColor(0xFFFFFFFF);
            windTv.setTextColor(0xFFFFFFFF);
        }
    }

    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        switch (v.getId()) {
            case R.id.frag_index_tv_dress:
                builder.setTitle("穿衣建议");
                String msg = "暂无信息";
                if (lifeBean!=null){
                    msg = lifeBean.getChuanyi().getV()+"\n"+lifeBean.getChuanyi().getDes();
                }
                builder.setMessage(msg);
                builder.setPositiveButton("确定",null);
                break;
            case R.id.frag_index_tv_washcar:
                builder.setTitle("洗车建议");
                msg = "暂无信息";
                if (lifeBean!=null){
                    msg = lifeBean.getXiche().getV()+"\n"+lifeBean.getXiche().getDes();
                }
                builder.setMessage(msg);
                builder.setPositiveButton("确定",null);
                break;
            case R.id.frag_index_tv_cold:
                builder.setTitle("感冒情况");
                msg = "暂无信息";
                if (lifeBean!=null){
                    msg = lifeBean.getGanmao().getV()+"\n"+lifeBean.getGanmao().getDes();
                }
                builder.setMessage(msg);
                builder.setPositiveButton("确定",null);
                break;
            case R.id.frag_index_tv_sport:
                builder.setTitle("运动建议");
                msg = "暂无信息";
                if (lifeBean!=null){
                    msg = lifeBean.getYundong().getV()+"\n"+lifeBean.getYundong().getDes();
                }
                builder.setMessage(msg);
                builder.setPositiveButton("确定",null);
                break;
            case R.id.frag_index_tv_rays:
                builder.setTitle("紫外线强度");
                msg = "暂无信息";
                if (lifeBean!=null){
                    msg = lifeBean.getZiwaixian().getV()+"\n"+lifeBean.getZiwaixian().getDes();
                }
                builder.setMessage(msg);
                builder.setPositiveButton("确定",null);
                break;
            case R.id.frag_index_tv_air:
                builder.setTitle("舒适度");
                msg = "暂无信息";
                if (lifeBean!=null){
                    msg = lifeBean.getShushidu().getV()+"\n"+lifeBean.getShushidu().getDes();
                }
                builder.setMessage(msg);
                builder.setPositiveButton("确定",null);
                break;
        }
        builder.create().show();
    }
}
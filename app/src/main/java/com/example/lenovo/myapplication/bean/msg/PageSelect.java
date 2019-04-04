package com.example.lenovo.myapplication.bean.msg;


/**
 * Created by lenovo on 2018/8/25.
 * auther:lenovo
 * Date：2018/8/25
 */
public class PageSelect  {
    private String collect_num;
    private int position;
    public String getCollect_num() {
        return collect_num;
    }

    public int getPosition() {
        return position;
    }

    public PageSelect(String collect_num, int position) {
        this.collect_num = collect_num;
        this.position = position;
    }
}

package com.example.lenovo.myapplication.bean;

import android.support.annotation.NonNull;

/**
 * Created by lenovo on 2018/9/2.
 * auther:lenovo
 * Date：2018/9/2
 */
public class VideoListBean implements Comparable<VideoListBean>{


    /**
     * comment_num : 0
     * gmt_create : 1535099376000
     * video_height : 404
     * video_duration : 0:15
     * cover_url : /1db962c76d1007f2f96320d41dc89f86.jpg
     * tag_name : 模特,比基尼
     * video_width : 720
     * collect_num : 230
     * cover_height : 360
     * sort : 0
     * click_num : 12743
     * title : 诱惑力十足！性感尤物大尺度写真，让人充血！
     * video_url : http://pr.mm798.net/back/20180824/137ee21589f17266ea6545c0aa705f71.mp4
     * cover_width : 640
     * thumb_num : 48
     * avid : 5069
     */

    private int comment_num;
    private long gmt_create;
    private int video_height;
    private String video_duration;
    private String cover_url;
    private String tag_name;
    private int video_width;
    private int collect_num;
    private int cover_height;
    private int sort;
    private int click_num;
    private String title;
    private String video_url;
    private int cover_width;
    private int thumb_num;
    private int avid;

    public int getComment_num() {
        return comment_num;
    }

    public void setComment_num(int comment_num) {
        this.comment_num = comment_num;
    }

    public long getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(long gmt_create) {
        this.gmt_create = gmt_create;
    }

    public int getVideo_height() {
        return video_height;
    }

    public void setVideo_height(int video_height) {
        this.video_height = video_height;
    }

    public String getVideo_duration() {
        return video_duration;
    }

    public void setVideo_duration(String video_duration) {
        this.video_duration = video_duration;
    }

    public String getCover_url() {
        return cover_url;
    }

    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    public int getVideo_width() {
        return video_width;
    }

    public void setVideo_width(int video_width) {
        this.video_width = video_width;
    }

    public int getCollect_num() {
        return collect_num;
    }

    public void setCollect_num(int collect_num) {
        this.collect_num = collect_num;
    }

    public int getCover_height() {
        return cover_height;
    }

    public void setCover_height(int cover_height) {
        this.cover_height = cover_height;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getClick_num() {
        return click_num;
    }

    public void setClick_num(int click_num) {
        this.click_num = click_num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public int getCover_width() {
        return cover_width;
    }

    public void setCover_width(int cover_width) {
        this.cover_width = cover_width;
    }

    public int getThumb_num() {
        return thumb_num;
    }

    public void setThumb_num(int thumb_num) {
        this.thumb_num = thumb_num;
    }

    public int getAvid() {
        return avid;
    }

    public void setAvid(int avid) {
        this.avid = avid;
    }
    @Override
    public int compareTo(@NonNull VideoListBean o) {
        return  this.avid- o.getAvid();
    }
}

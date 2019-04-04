package com.example.lenovo.myapplication.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lenovo on 2018/8/26.
 * auther:lenovo
 * Date：2018/8/26
 */
public class MyCollection {
    public final static int PIC = 0x1;
    public final static int VEDIO = 0x2;

    public List<DataBean> getList() {
        return list;
    }

    public void setList(List<DataBean> list) {
        this.list = list;
    }

    private List<DataBean> list;

    public static class DataBean {
        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        private int type;
    }

    public static class PicBean extends DataBean {
        /**
         * apid : null
         * avid : 4845
         * pid : null
         * collectionType : 2
         * createTime : 2018-08-12 05:52:50
         */

        private String apid;
        private int avid;
        private String pid;
        private int collectionType;
        private String createTime;
        /**
         * comment_num : 51
         * apid : 3164
         * collect_num : 2210
         * thumb_num : 3266
         * title : 尤果美女香川颖白嫩美胸甜美可爱
         */

        private int comment_num;
        @SerializedName("apid")
        private int apidX;
        private int collect_num;
        private int thumb_num;
        private String title;
        /**
         * width : 700
         * id : 203999
         * collection_num : 8842
         * pic_url : /pic/3164/1.jpg
         * height : 1050
         */

        private int width;
        private int id;
        private int collection_num;
        private String pic_url;
        private int height;

        public String getApid() {
            return apid;
        }

        public void setApid(String apid) {
            this.apid = apid;
        }

        public int getAvid() {
            return avid;
        }

        public void setAvid(int avid) {
            this.avid = avid;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public int getCollectionType() {
            return collectionType;
        }

        public void setCollectionType(int collectionType) {
            this.collectionType = collectionType;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getComment_num() {
            return comment_num;
        }

        public void setComment_num(int comment_num) {
            this.comment_num = comment_num;
        }

        public int getApidX() {
            return apidX;
        }

        public void setApidX(int apidX) {
            this.apidX = apidX;
        }

        public int getCollect_num() {
            return collect_num;
        }

        public void setCollect_num(int collect_num) {
            this.collect_num = collect_num;
        }

        public int getThumb_num() {
            return thumb_num;
        }

        public void setThumb_num(int thumb_num) {
            this.thumb_num = thumb_num;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCollection_num() {
            return collection_num;
        }

        public void setCollection_num(int collection_num) {
            this.collection_num = collection_num;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }

    public static class VideoBean extends DataBean {

        /**
         * comment_num : 4
         * video_duration : 0:24
         * video_height : 404
         * cover_url : /ddd4bfd9a4dd6c29a993f457ef0d2644.jpg
         * video_width : 720
         * collect_num : 1288
         * click_num : 54267
         * cover_height : 360
         * title : 请问这个妹子我能打包带回家吗啊？
         * video_url : http://pr.mm798.net/back/20180803/143f4150089dae4e290d6d47d8306d23.mp4
         * cover_width : 640
         * thumb_num : 300
         * avid : 4844
         */

        private int comment_num;
        private String video_duration;
        private int video_height;
        private String cover_url;
        private int video_width;
        private int collect_num;
        private int click_num;
        private int cover_height;
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

        public String getVideo_duration() {
            return video_duration;
        }

        public void setVideo_duration(String video_duration) {
            this.video_duration = video_duration;
        }

        public int getVideo_height() {
            return video_height;
        }

        public void setVideo_height(int video_height) {
            this.video_height = video_height;
        }

        public String getCover_url() {
            return cover_url;
        }

        public void setCover_url(String cover_url) {
            this.cover_url = cover_url;
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

        public int getClick_num() {
            return click_num;
        }

        public void setClick_num(int click_num) {
            this.click_num = click_num;
        }

        public int getCover_height() {
            return cover_height;
        }

        public void setCover_height(int cover_height) {
            this.cover_height = cover_height;
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
    }
}
package com.example.lenovo.myapplication.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lenovo on 2018/8/12.
 * auther:lenovo
 * Date：2018/8/12
 */

public class NewVideo implements Serializable {

    private String msg;
    private int code;
    private DataObjBean dataObj;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataObjBean getDataObj() {
        return dataObj;
    }

    public void setDataObj(DataObjBean dataObj) {
        this.dataObj = dataObj;
    }

    public static class DataObjBean {


        private int maxVideoId;
        private int lastIndex;
        private List<NewVideoListBean> newVideoList;

        public int getMaxVideoId() {
            return maxVideoId;
        }

        public void setMaxVideoId(int maxVideoId) {
            this.maxVideoId = maxVideoId;
        }

        public int getLastIndex() {
            return lastIndex;
        }

        public void setLastIndex(int lastIndex) {
            this.lastIndex = lastIndex;
        }

        public List<NewVideoListBean> getNewVideoList() {
            return newVideoList;
        }

        public void setNewVideoList(List<NewVideoListBean> newVideoList) {
            this.newVideoList = newVideoList;
        }

        public static class NewVideoListBean implements Serializable{
            /**
             * comment_num : 2
             * gmt_create : 2018-08-03 15:03:46
             * cover_bak : null
             * video_height : 800
             * video_duration : 0:07
             * cover_url : /e04b7e47a04208daed9d77d5d1a5b770.jpg
             * tag_name : 自拍,诱惑
             * video_width : 640
             * collect_num : 839
             * cover_height : 360
             * click_num : 25172
             * sort : 0
             * title : 这个妹子有点小可爱，有点小俏皮，让我看的有点小心动
             * content :
             * video_url : http://pr.mm798.net/back/20180803/01228b49d984bf877c6bf4d517c843f5.mp4
             * article_video_type : null
             * cover_width : 640
             * tag_id : null
             * thumb_num : 119
             * avid : 4845
             */

            private String comment_num;
            private String gmt_create;
            private Object cover_bak;
            private String video_height;
            private String video_duration;
            private String cover_url;
            private String tag_name;
            private String video_width;
            private String collect_num;
            private String cover_height;
            private int click_num;
            private String sort;
            private String title;
            private String content;
            private String video_url;
            private Object article_video_type;
            private String cover_width;
            private Object tag_id;
            private String thumb_num;
            private int avid;

            public String getComment_num() {
                return comment_num;
            }

            public void setComment_num(String comment_num) {
                this.comment_num = comment_num;
            }

            public String getGmt_create() {
                return gmt_create;
            }

            public void setGmt_create(String gmt_create) {
                this.gmt_create = gmt_create;
            }

            public Object getCover_bak() {
                return cover_bak;
            }

            public void setCover_bak(Object cover_bak) {
                this.cover_bak = cover_bak;
            }

            public String getVideo_height() {
                return video_height;
            }

            public void setVideo_height(String video_height) {
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

            public String getVideo_width() {
                return video_width;
            }

            public void setVideo_width(String video_width) {
                this.video_width = video_width;
            }

            public String getCollect_num() {
                return collect_num;
            }

            public void setCollect_num(String collect_num) {
                this.collect_num = collect_num;
            }

            public String getCover_height() {
                return cover_height;
            }

            public void setCover_height(String cover_height) {
                this.cover_height = cover_height;
            }

            public int getClick_num() {
                return click_num;
            }

            public void setClick_num(int click_num) {
                this.click_num = click_num;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getVideo_url() {
                return video_url;
            }

            public void setVideo_url(String video_url) {
                this.video_url = video_url;
            }

            public Object getArticle_video_type() {
                return article_video_type;
            }

            public void setArticle_video_type(Object article_video_type) {
                this.article_video_type = article_video_type;
            }

            public String getCover_width() {
                return cover_width;
            }

            public void setCover_width(String cover_width) {
                this.cover_width = cover_width;
            }

            public Object getTag_id() {
                return tag_id;
            }

            public void setTag_id(Object tag_id) {
                this.tag_id = tag_id;
            }

            public String getThumb_num() {
                return thumb_num;
            }

            public void setThumb_num(String thumb_num) {
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
}

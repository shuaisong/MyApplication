package com.example.lenovo.myapplication.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lenovo on 2018/8/12.
 * auther:lenovo
 * Date：2018/8/12
 */

public class Collection implements Serializable {

    /**
     * msg : 成功
     * code : 200
     * dataObj : {"collection":[{"apid":null,"avid":4845,"pid":null,"collectionType":2,"createTime":"2018-08-12 05:52:50"},{"apid":null,"avid":4844,"pid":null,"collectionType":2,"createTime":"2018-08-12 05:52:54"},{"apid":4124,"avid":null,"pid":255622,"collectionType":3,"createTime":"2018-08-25 18:25:46"},{"apid":3164,"avid":null,"pid":203999,"collectionType":3,"createTime":"2018-08-26 20:13:13"}],"picCollectionNum":2,"videoCollectionNum":2,"articlePicNum":[{"comment_num":51,"apid":3164,"collect_num":2210,"thumb_num":3266,"title":"尤果美女香川颖白嫩美胸甜美可爱"},{"comment_num":77,"apid":4124,"collect_num":0,"thumb_num":3659,"title":"轻灵仙子杨晨晨真空上阵摄人心魄"}],"articleVideoDetail":[{"comment_num":4,"video_duration":"0:24","video_height":404,"cover_url":"/ddd4bfd9a4dd6c29a993f457ef0d2644.jpg","video_width":720,"collect_num":1288,"click_num":54267,"cover_height":360,"title":"请问这个妹子我能打包带回家吗啊？","video_url":"http://pr.mm798.net/back/20180803/143f4150089dae4e290d6d47d8306d23.mp4","cover_width":640,"thumb_num":300,"avid":4844},{"comment_num":3,"video_duration":"0:07","video_height":800,"cover_url":"/e04b7e47a04208daed9d77d5d1a5b770.jpg","video_width":640,"collect_num":1468,"click_num":43551,"cover_height":360,"title":"这个妹子有点小可爱，有点小俏皮，让我看的有点小心动","video_url":"http://pr.mm798.net/back/20180803/01228b49d984bf877c6bf4d517c843f5.mp4","cover_width":640,"thumb_num":213,"avid":4845}],"picDetail":[{"width":700,"id":203999,"collection_num":8842,"pic_url":"/pic/3164/1.jpg","height":1050},{"width":700,"id":255622,"collection_num":5416,"pic_url":"/pic/4124/1.jpg","height":1050}],"lastIndex":0}
     */

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
        /**
         * collection : [{"apid":null,"avid":4845,"pid":null,"collectionType":2,"createTime":"2018-08-12 05:52:50"},{"apid":null,"avid":4844,"pid":null,"collectionType":2,"createTime":"2018-08-12 05:52:54"},{"apid":4124,"avid":null,"pid":255622,"collectionType":3,"createTime":"2018-08-25 18:25:46"},{"apid":3164,"avid":null,"pid":203999,"collectionType":3,"createTime":"2018-08-26 20:13:13"}]
         * picCollectionNum : 2
         * videoCollectionNum : 2
         * articlePicNum : [{"comment_num":51,"apid":3164,"collect_num":2210,"thumb_num":3266,"title":"尤果美女香川颖白嫩美胸甜美可爱"},{"comment_num":77,"apid":4124,"collect_num":0,"thumb_num":3659,"title":"轻灵仙子杨晨晨真空上阵摄人心魄"}]
         * articleVideoDetail : [{"comment_num":4,"video_duration":"0:24","video_height":404,"cover_url":"/ddd4bfd9a4dd6c29a993f457ef0d2644.jpg","video_width":720,"collect_num":1288,"click_num":54267,"cover_height":360,"title":"请问这个妹子我能打包带回家吗啊？","video_url":"http://pr.mm798.net/back/20180803/143f4150089dae4e290d6d47d8306d23.mp4","cover_width":640,"thumb_num":300,"avid":4844},{"comment_num":3,"video_duration":"0:07","video_height":800,"cover_url":"/e04b7e47a04208daed9d77d5d1a5b770.jpg","video_width":640,"collect_num":1468,"click_num":43551,"cover_height":360,"title":"这个妹子有点小可爱，有点小俏皮，让我看的有点小心动","video_url":"http://pr.mm798.net/back/20180803/01228b49d984bf877c6bf4d517c843f5.mp4","cover_width":640,"thumb_num":213,"avid":4845}]
         * picDetail : [{"width":700,"id":203999,"collection_num":8842,"pic_url":"/pic/3164/1.jpg","height":1050},{"width":700,"id":255622,"collection_num":5416,"pic_url":"/pic/4124/1.jpg","height":1050}]
         * lastIndex : 0
         */

        private int picCollectionNum;
        private int videoCollectionNum;
        private int lastIndex;
        private List<CollectionBean> collection;
        private List<ArticlePicNumBean> articlePicNum;
        private List<ArticleVideoDetailBean> articleVideoDetail;
        private List<PicDetailBean> picDetail;

        public int getPicCollectionNum() {
            return picCollectionNum;
        }

        public void setPicCollectionNum(int picCollectionNum) {
            this.picCollectionNum = picCollectionNum;
        }

        public int getVideoCollectionNum() {
            return videoCollectionNum;
        }

        public void setVideoCollectionNum(int videoCollectionNum) {
            this.videoCollectionNum = videoCollectionNum;
        }

        public int getLastIndex() {
            return lastIndex;
        }

        public void setLastIndex(int lastIndex) {
            this.lastIndex = lastIndex;
        }

        public List<CollectionBean> getCollection() {
            return collection;
        }

        public void setCollection(List<CollectionBean> collection) {
            this.collection = collection;
        }

        public List<ArticlePicNumBean> getArticlePicNum() {
            return articlePicNum;
        }

        public void setArticlePicNum(List<ArticlePicNumBean> articlePicNum) {
            this.articlePicNum = articlePicNum;
        }

        public List<ArticleVideoDetailBean> getArticleVideoDetail() {
            return articleVideoDetail;
        }

        public void setArticleVideoDetail(List<ArticleVideoDetailBean> articleVideoDetail) {
            this.articleVideoDetail = articleVideoDetail;
        }

        public List<PicDetailBean> getPicDetail() {
            return picDetail;
        }

        public void setPicDetail(List<PicDetailBean> picDetail) {
            this.picDetail = picDetail;
        }

        public static class CollectionBean {
            /**
             * apid : null
             * avid : 4845
             * pid : null
             * collectionType : 2
             * createTime : 2018-08-12 05:52:50
             */

            private Object apid;
            private int avid;
            private Object pid;
            private int collectionType;
            private String createTime;

            public Object getApid() {
                return apid;
            }

            public void setApid(Object apid) {
                this.apid = apid;
            }

            public int getAvid() {
                return avid;
            }

            public void setAvid(int avid) {
                this.avid = avid;
            }

            public Object getPid() {
                return pid;
            }

            public void setPid(Object pid) {
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
        }

        public static class ArticlePicNumBean {
            /**
             * comment_num : 51
             * apid : 3164
             * collect_num : 2210
             * thumb_num : 3266
             * title : 尤果美女香川颖白嫩美胸甜美可爱
             */

            private int comment_num;
            private int apid;
            private int collect_num;
            private int thumb_num;
            private String title;

            public int getComment_num() {
                return comment_num;
            }

            public void setComment_num(int comment_num) {
                this.comment_num = comment_num;
            }

            public int getApid() {
                return apid;
            }

            public void setApid(int apid) {
                this.apid = apid;
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
        }

        public static class ArticleVideoDetailBean {
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

        public static class PicDetailBean {
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
    }
}

package com.example.lenovo.myapplication.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lenovo on 2018/8/12.
 * auther:lenovo
 * Date：2018/8/12
 */

public class RecommendLabel implements Serializable {

    /**
     * msg : 成功
     * code : 200
     * dataObj : {"videoLabelList":[{"thumb_url":"/20171220/541c05364ceba75060ac42371c0dd123.jpg","content_num":971,"back_url":"/20171220/414a9a86870350d0029370a6476cc652.jpg","label_hot":1646157,"label_type":2,"id":10,"label_name":"自拍"},{"thumb_url":"/20171220/2f11a00f118a30d798bf22aa786e53d4.png","content_num":117,"back_url":"/20171220/93cd8b62d409cbeeec2e121fa0d88ecb.png","label_hot":1297030,"label_type":2,"id":13,"label_name":"热舞"},{"thumb_url":"/20171220/98a6b5234432f24af138fb0d5397a7a3.jpg","content_num":169,"back_url":"/20171220/8534b51ad3f08854265067956c5194c7.jpg","label_hot":1576194,"label_type":2,"id":12,"label_name":"主播"},{"thumb_url":"/20171220/2b75c3bc52de592b2fdd740e73dd39d6.jpg","content_num":830,"back_url":"/20171220/e3d8d64dec3d239471ef014be8486c86.jpg","label_hot":2580819,"label_type":2,"id":9,"label_name":"写真"}],"articleLabelList":[{"thumb_url":"/20171220/e7cb3d58d5fc248ab319614ca9141c3c.jpg","content_num":156,"back_url":"/20171220/e414f0d06f56f6877b166dba599b2046.jpg","label_hot":4002876,"label_type":1,"id":8,"label_name":"黑丝"},{"thumb_url":"/20171220/77f1b8e617c4d25f4a139a3024d1e6f9.jpg","content_num":673,"back_url":"/20171220/icon.jpg_type":1,"id":6,"label_name":"翘臀"},{"thumb_url":"/20171220/0cc56d4e57816e89d0dffb350c482d0c.jpg","content_num":49,"back_url":"/20171220/ba674be2fba0484358088597fed1ff13.jpg","label_hot":1880885,"label_type":1,"id":5,"label_name":"美腿"},{"thumb_url":"/20171220/c5f3b0e5cf2035f49278066b1ecc970e.jpg","content_num":1537,"back_url":"/20171220/35b5761f2224e2acd219f446394c83a7.jpg?0","label_hot":6010240,"label_type":1,"id":3,"label_name":"尤物"}]}
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
        private List<VideoLabelListBean> videoLabelList;
        private List<ArticleLabelListBean> articleLabelList;

        public List<VideoLabelListBean> getVideoLabelList() {
            return videoLabelList;
        }

        public void setVideoLabelList(List<VideoLabelListBean> videoLabelList) {
            this.videoLabelList = videoLabelList;
        }

        public List<ArticleLabelListBean> getArticleLabelList() {
            return articleLabelList;
        }

        public void setArticleLabelList(List<ArticleLabelListBean> articleLabelList) {
            this.articleLabelList = articleLabelList;
        }
        public static class LabelListBean implements Serializable{
            private String thumb_url;
            private int content_num;
            private String back_url;
            private int label_hot;
            private int label_type;
            private int id;
            private String label_name;

            public String getThumb_url() {
                return thumb_url;
            }

            public void setThumb_url(String thumb_url) {
                this.thumb_url = thumb_url;
            }

            public int getContent_num() {
                return content_num;
            }

            public void setContent_num(int content_num) {
                this.content_num = content_num;
            }

            public String getBack_url() {
                return back_url;
            }

            public void setBack_url(String back_url) {
                this.back_url = back_url;
            }

            public int getLabel_hot() {
                return label_hot;
            }

            public void setLabel_hot(int label_hot) {
                this.label_hot = label_hot;
            }

            public int getLabel_type() {
                return label_type;
            }

            public void setLabel_type(int label_type) {
                this.label_type = label_type;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLabel_name() {
                return label_name;
            }

            public void setLabel_name(String label_name) {
                this.label_name = label_name;
            }
        }
        public static class VideoLabelListBean extends LabelListBean  {
            /**
             * thumb_url : /20171220/541c05364ceba75060ac42371c0dd123.jpg
             * content_num : 971
             * back_url : /20171220/414a9a86870350d0029370a6476cc652.jpg
             * label_hot : 1646157
             * label_type : 2
             * id : 10
             * label_name : 自拍
             */
        }

        public static class ArticleLabelListBean extends LabelListBean   {
            /**
             * thumb_url : /20171220/e7cb3d58d5fc248ab319614ca9141c3c.jpg
             * content_num : 156
             * back_url : /20171220/e414f0d06f56f6877b166dba599b2046.jpg
             * label_hot : 4002876
             * label_type : 1
             * id : 8
             * label_name : 黑丝
             */

           /* private String thumb_url;
            private int content_num;
            private String back_url;
            private int label_hot;
            private int label_type;
            private int id;
            private String label_name;

            public String getThumb_url() {
                return thumb_url;
            }

            public void setThumb_url(String thumb_url) {
                this.thumb_url = thumb_url;
            }

            public int getContent_num() {
                return content_num;
            }

            public void setContent_num(int content_num) {
                this.content_num = content_num;
            }

            public String getBack_url() {
                return back_url;
            }

            public void setBack_url(String back_url) {
                this.back_url = back_url;
            }

            public int getLabel_hot() {
                return label_hot;
            }

            public void setLabel_hot(int label_hot) {
                this.label_hot = label_hot;
            }

            public int getLabel_type() {
                return label_type;
            }

            public void setLabel_type(int label_type) {
                this.label_type = label_type;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLabel_name() {
                return label_name;
            }

            public void setLabel_name(String label_name) {
                this.label_name = label_name;
            }*/
        }
    }
}

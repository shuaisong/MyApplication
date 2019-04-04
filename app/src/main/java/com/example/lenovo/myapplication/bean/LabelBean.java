package com.example.lenovo.myapplication.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lenovo on 2018/9/2.
 * auther:lenovo
 * Date：2018/9/2
 */
public class LabelBean implements Serializable {

    /**
     * msg : 成功
     * code : 200
     * dataObj : {"labelList":[{"thumb_url":"http://pr.mm798.net/back/20171220/2b75c3bc52de592b2fdd740e73dd39d6.jpg","content_num":864,"back_url":"http://pr.mm798.net/back/20171220/e3d8d64dec3d239471ef014be8486c86.jpg","label_hot":2791912,"label_type":2,"id":9,"label_name":"写真"},{"thumb_url":"http://pr.mm798.net/back/20171220/541c05364ceba75060ac42371c0dd123.jpg","content_num":1122,"back_url":"http://pr.mm798.net/back/20171220/414a9a86870350d0029370a6476cc652.jpg","label_hot":1799663,"label_type":2,"id":10,"label_name":"自拍"},{"thumb_url":"http://pr.mm798.net/back/20171220/44a274a40a9684b8657e2ddbe2418f07.jpg","content_num":807,"back_url":"http://pr.mm798.net/back/20171220/34ac06a02a5d37d8e7e9fa59cc716fa0.jpg","label_hot":357709,"label_type":2,"id":11,"label_name":"模特"},{"thumb_url":"http://pr.mm798.net/back/20171220/98a6b5234432f24af138fb0d5397a7a3.jpg","content_num":179,"back_url":"http://pr.mm798.net/back/20171220/8534b51ad3f08854265067956c5194c7.jpg","label_hot":1729379,"label_type":2,"id":12,"label_name":"主播"},{"thumb_url":"http://pr.mm798.net/back/20171220/2f11a00f118a30d798bf22aa786e53d4.png","content_num":142,"back_url":"http://pr.mm798.net/back/20171220/93cd8b62d409cbeeec2e121fa0d88ecb.png","label_hot":1438479,"label_type":2,"id":13,"label_name":"热舞"},{"thumb_url":"http://pr.mm798.net/back/20171220/5096aaca37c1bb3fd34e2c65bbfd8d4b.jpg","content_num":1023,"back_url":"http://pr.mm798.net/back/20171220/70854b91d8a2e9189b835808903c62f6.jpg","label_hot":399977,"label_type":2,"id":14,"label_name":"比基尼"},{"thumb_url":"http://pr.mm798.net/back/20171220/61f96ce3783ecfa8d4c589267a266df3.jpg","content_num":2953,"back_url":"http://pr.mm798.net/back/20171220/5c22fc301b115ffa91c4dc53aa51fb09.jpg","label_hot":2058717,"label_type":2,"id":15,"label_name":"诱惑"},{"thumb_url":"http://pr.mm798.net/back/20171220/7b8cb9c5570ac6f30f7b254f05882b00.jpg","content_num":65,"back_url":"http://pr.mm798.net/back/20171220/40ee5a847f547fcc2e8e02ead93ceca2.jpg","label_hot":334497,"label_type":2,"id":16,"label_name":"运动"}]}
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
        private List<LabelListBean> labelList;

        public List<LabelListBean> getLabelList() {
            return labelList;
        }

        public void setLabelList(List<LabelListBean> labelList) {
            this.labelList = labelList;
        }

        public static class LabelListBean {
            /**
             * thumb_url : http://pr.mm798.net/back/20171220/2b75c3bc52de592b2fdd740e73dd39d6.jpg
             * content_num : 864
             * back_url : http://pr.mm798.net/back/20171220/e3d8d64dec3d239471ef014be8486c86.jpg
             * label_hot : 2791912
             * label_type : 2
             * id : 9
             * label_name : 写真
             */

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
    }
}

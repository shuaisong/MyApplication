package com.example.lenovo.myapplication.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lenovo on 2018/8/12.
 * auther:lenovo
 * Date：2018/8/12
 */

public class DetailImgUrl implements Serializable {

    /**
     * msg : 成功
     * code : 200
     * dataObj : {"picUrlList":[{"apid":4244,"width":700,"id":263977,"collection_num":1412,"sort":0,"pic_url":"/pic/4244/1.jpg","height":1050},{"apid":4244,"width":700,"id":263978,"collection_num":135,"sort":0,"pic_url":"/pic/4244/2.jpg","height":1050},{"apid":4244,"width":700,"id":263979,"collection_num":77,"sort":0,"pic_url":"/pic/4244/3.jpg","height":1050},{"apid":4244,"width":700,"id":263980,"collection_num":154,"sort":0,"pic_url":"/pic/4244/4.jpg","height":1050},{"apid":4244,"width":700,"id":263981,"collection_num":168,"sort":0,"pic_url":"/pic/4244/5.jpg","height":1050},{"apid":4244,"width":700,"id":263982,"collection_num":97,"sort":0,"pic_url":"/pic/4244/6.jpg","height":1050},{"apid":4244,"width":700,"id":263983,"collection_num":179,"sort":0,"pic_url":"/pic/4244/7.jpg","height":1050},{"apid":4244,"width":700,"id":263984,"collection_num":114,"sort":0,"pic_url":"/pic/4244/8.jpg","height":1050},{"apid":4244,"width":700,"id":263985,"collection_num":67,"sort":0,"pic_url":"/pic/4244/9.jpg","height":1050},{"apid":4244,"width":700,"id":263986,"collection_num":71,"sort":0,"pic_url":"/pic/4244/10.jpg","height":1050},{"apid":4244,"width":700,"id":263987,"collection_num":213,"sort":0,"pic_url":"/pic/4244/11.jpg","height":1050},{"apid":4244,"width":700,"id":263988,"collection_num":44,"sort":0,"pic_url":"/pic/4244/12.jpg","height":1050},{"apid":4244,"width":700,"id":263989,"collection_num":177,"sort":0,"pic_url":"/pic/4244/13.jpg","height":1050},{"apid":4244,"width":700,"id":263990,"collection_num":154,"sort":0,"pic_url":"/pic/4244/14.jpg","height":1050},{"apid":4244,"width":700,"id":263991,"collection_num":168,"sort":0,"pic_url":"/pic/4244/15.jpg","height":1050},{"apid":4244,"width":700,"id":263992,"collection_num":228,"sort":0,"pic_url":"/pic/4244/16.jpg","height":1050},{"apid":4244,"width":700,"id":263993,"collection_num":116,"sort":0,"pic_url":"/pic/4244/17.jpg","height":1050},{"apid":4244,"width":700,"id":263994,"collection_num":267,"sort":0,"pic_url":"/pic/4244/18.jpg","height":1050},{"apid":4244,"width":700,"id":263995,"collection_num":159,"sort":0,"pic_url":"/pic/4244/19.jpg","height":1050},{"apid":4244,"width":700,"id":263996,"collection_num":72,"sort":0,"pic_url":"/pic/4244/20.jpg","height":1050},{"apid":4244,"width":700,"id":263997,"collection_num":95,"sort":0,"pic_url":"/pic/4244/21.jpg","height":1050},{"apid":4244,"width":700,"id":263998,"collection_num":127,"sort":0,"pic_url":"/pic/4244/22.jpg","height":1050},{"apid":4244,"width":700,"id":263999,"collection_num":185,"sort":0,"pic_url":"/pic/4244/23.jpg","height":1050},{"apid":4244,"width":800,"id":264000,"collection_num":383,"sort":0,"pic_url":"/pic/4244/24.jpg","height":533},{"apid":4244,"width":800,"id":264001,"collection_num":62,"sort":0,"pic_url":"/pic/4244/25.jpg","height":533},{"apid":4244,"width":700,"id":264002,"collection_num":408,"sort":0,"pic_url":"/pic/4244/26.jpg","height":1050},{"apid":4244,"width":700,"id":264003,"collection_num":191,"sort":0,"pic_url":"/pic/4244/27.jpg","height":1050},{"apid":4244,"width":700,"id":264004,"collection_num":94,"sort":0,"pic_url":"/pic/4244/28.jpg","height":1050},{"apid":4244,"width":700,"id":264005,"collection_num":296,"sort":0,"pic_url":"/pic/4244/29.jpg","height":1050},{"apid":4244,"width":700,"id":264006,"collection_num":562,"sort":0,"pic_url":"/pic/4244/30.jpg","height":1050},{"apid":4244,"width":700,"id":264007,"collection_num":338,"sort":0,"pic_url":"/pic/4244/31.jpg","height":1050},{"apid":4244,"width":700,"id":264008,"collection_num":261,"sort":0,"pic_url":"/pic/4244/32.jpg","height":1050},{"apid":4244,"width":700,"id":264009,"collection_num":200,"sort":0,"pic_url":"/pic/4244/33.jpg","height":1050},{"apid":4244,"width":700,"id":264010,"collection_num":145,"sort":0,"pic_url":"/pic/4244/34.jpg","height":1050},{"apid":4244,"width":700,"id":264011,"collection_num":204,"sort":0,"pic_url":"/pic/4244/35.jpg","height":1050},{"apid":4244,"width":700,"id":264012,"collection_num":124,"sort":0,"pic_url":"/pic/4244/36.jpg","height":1050},{"apid":4244,"width":700,"id":264013,"collection_num":234,"sort":0,"pic_url":"/pic/4244/37.jpg","height":1050},{"apid":4244,"width":700,"id":264014,"collection_num":236,"sort":0,"pic_url":"/pic/4244/38.jpg","height":1050},{"apid":4244,"width":700,"id":264015,"collection_num":168,"sort":0,"pic_url":"/pic/4244/39.jpg","height":1050},{"apid":4244,"width":700,"id":264016,"collection_num":54,"sort":0,"pic_url":"/pic/4244/40.jpg","height":1050},{"apid":4244,"width":700,"id":264017,"collection_num":97,"sort":0,"pic_url":"/pic/4244/41.jpg","height":1050},{"apid":4244,"width":700,"id":264018,"collection_num":68,"sort":0,"pic_url":"/pic/4244/42.jpg","height":1050},{"apid":4244,"width":700,"id":264019,"collection_num":209,"sort":0,"pic_url":"/pic/4244/43.jpg","height":1050},{"apid":4244,"width":700,"id":264020,"collection_num":129,"sort":0,"pic_url":"/pic/4244/44.jpg","height":1050},{"apid":4244,"width":700,"id":264021,"collection_num":293,"sort":0,"pic_url":"/pic/4244/45.jpg","height":1050},{"apid":4244,"width":700,"id":264022,"collection_num":141,"sort":0,"pic_url":"/pic/4244/46.jpg","height":1050},{"apid":4244,"width":700,"id":264023,"collection_num":85,"sort":0,"pic_url":"/pic/4244/47.jpg","height":1050},{"apid":4244,"width":700,"id":264024,"collection_num":215,"sort":0,"pic_url":"/pic/4244/48.jpg","height":1050},{"apid":4244,"width":700,"id":264025,"collection_num":215,"sort":0,"pic_url":"/pic/4244/49.jpg","height":1050},{"apid":4244,"width":700,"id":264026,"collection_num":160,"sort":0,"pic_url":"/pic/4244/50.jpg","height":1050}]}
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
        private List<PicUrlListBean> picUrlList;

        public List<PicUrlListBean> getPicUrlList() {
            return picUrlList;
        }

        public void setPicUrlList(List<PicUrlListBean> picUrlList) {
            this.picUrlList = picUrlList;
        }

        public static class PicUrlListBean {
            /**
             * apid : 4244
             * width : 700
             * id : 263977
             * collection_num : 1412
             * sort : 0
             * pic_url : /pic/4244/1.jpg
             * height : 1050
             */

            private int apid;
            private int width;
            private int id;
            private int collection_num;
            private int sort;
            private String pic_url;
            private int height;

            public int getApid() {
                return apid;
            }

            public void setApid(int apid) {
                this.apid = apid;
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

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
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

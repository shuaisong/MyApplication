package com.example.lenovo.myapplication.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lenovo on 2018/8/12.
 * auther:lenovo
 * Date：2018/8/12
 */

public class SearchType implements Serializable {

    /**
     * msg : 成功
     * code : 200
     * dataObj : {"keywordsList":[{"keywords":"巨乳","id":265,"search_type":1,"search_times":2056471},{"keywords":"性感","id":6,"search_type":1,"search_times":1332304},{"keywords":"激情","id":3,"search_type":1,"search_times":895889},{"keywords":"萝莉","id":58,"search_type":1,"search_times":761980},{"keywords":"美女","id":26,"search_type":1,"search_times":530847},{"keywords":"清纯","id":4,"search_type":1,"search_times":441946},{"keywords":"惹火","id":15,"search_type":1,"search_times":417344},{"keywords":"丰满","id":1,"search_type":1,"search_times":417038},{"keywords":"可爱","id":2,"search_type":1,"search_times":350815},{"keywords":"比基尼","id":5,"search_type":1,"search_times":350565}]}
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
        private List<KeywordsListBean> keywordsList;

        public List<KeywordsListBean> getKeywordsList() {
            return keywordsList;
        }

        public void setKeywordsList(List<KeywordsListBean> keywordsList) {
            this.keywordsList = keywordsList;
        }

        public static class KeywordsListBean {
            /**
             * keywords : 巨乳
             * id : 265
             * search_type : 1
             * search_times : 2056471
             */

            private String keywords;
            private int id;
            private int search_type;
            private int search_times;

            public String getKeywords() {
                return keywords;
            }

            public void setKeywords(String keywords) {
                this.keywords = keywords;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getSearch_type() {
                return search_type;
            }

            public void setSearch_type(int search_type) {
                this.search_type = search_type;
            }

            public int getSearch_times() {
                return search_times;
            }

            public void setSearch_times(int search_times) {
                this.search_times = search_times;
            }
        }
    }
}

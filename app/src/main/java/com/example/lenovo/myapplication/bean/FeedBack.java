package com.example.lenovo.myapplication.bean;

import java.io.Serializable;

/**
 * Created by lenovo on 2018/8/12.
 * auther:lenovo
 * Date：2018/8/12
 */

public class FeedBack implements Serializable {
    /**
     * msg : 成功
     * code : 200
     * dataObj : {"feedbackUnReadNum":0}
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
         * feedbackUnReadNum : 0
         */

        private int feedbackUnReadNum;

        public int getFeedbackUnReadNum() {
            return feedbackUnReadNum;
        }

        public void setFeedbackUnReadNum(int feedbackUnReadNum) {
            this.feedbackUnReadNum = feedbackUnReadNum;
        }
    }
}

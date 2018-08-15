package com.example.lenovo.myapplication.bean;

import java.io.Serializable;

/**
 * Created by lenovo on 2018/8/12.
 * auther:lenovo
 * Date：2018/8/12
 */

public class MemberInfo implements Serializable {

    /**
     * msg : 成功
     * code : 200
     * dataObj : {"memDate":"1990-01-01 00:00:00","miDouNum":0,"biz_code":200,"isMem":1}
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
         * memDate : 1990-01-01 00:00:00
         * miDouNum : 0
         * biz_code : 200
         * isMem : 1
         */

        private String memDate;
        private int miDouNum;
        private int biz_code;
        private int isMem;

        public String getMemDate() {
            return memDate;
        }

        public void setMemDate(String memDate) {
            this.memDate = memDate;
        }

        public int getMiDouNum() {
            return miDouNum;
        }

        public void setMiDouNum(int miDouNum) {
            this.miDouNum = miDouNum;
        }

        public int getBiz_code() {
            return biz_code;
        }

        public void setBiz_code(int biz_code) {
            this.biz_code = biz_code;
        }

        public int getIsMem() {
            return isMem;
        }

        public void setIsMem(int isMem) {
            this.isMem = isMem;
        }
    }
}

package com.example.lenovo.myapplication.bean;

import java.io.Serializable;

/**
 * Created by lenovo on 2018/8/12.
 * auther:lenovo
 * Date：2018/8/12
 */

public class VersionCode implements Serializable {


    /**
     * msg : 成功
     * code : 200
     * dataObj : {"showTitle":"http://118.31.170.252:880/","currentVersionStatus":2,"packageUrl":"http://apkcdn.kxtop.com/mm/1.7.2/mmpic_v1.7.2_1.apk","showTime":264,"updateContent":"           1.修复已知bug  \r\n           2.优化用户体验  \r\n           3.意见反馈升级","updateType":1}
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
         * showTitle : http://118.31.170.252:880/
         * currentVersionStatus : 2
         * packageUrl : http://apkcdn.kxtop.com/mm/1.7.2/mmpic_v1.7.2_1.apk
         * showTime : 264
         * updateContent :            1.修复已知bug
         * 2.优化用户体验
         * 3.意见反馈升级
         * updateType : 1
         */

        private String showTitle;
        private int currentVersionStatus;
        private String packageUrl;
        private int showTime;
        private String updateContent;
        private int updateType;

        public String getShowTitle() {
            return showTitle;
        }

        public void setShowTitle(String showTitle) {
            this.showTitle = showTitle;
        }

        public int getCurrentVersionStatus() {
            return currentVersionStatus;
        }

        public void setCurrentVersionStatus(int currentVersionStatus) {
            this.currentVersionStatus = currentVersionStatus;
        }

        public String getPackageUrl() {
            return packageUrl;
        }

        public void setPackageUrl(String packageUrl) {
            this.packageUrl = packageUrl;
        }

        public int getShowTime() {
            return showTime;
        }

        public void setShowTime(int showTime) {
            this.showTime = showTime;
        }

        public String getUpdateContent() {
            return updateContent;
        }

        public void setUpdateContent(String updateContent) {
            this.updateContent = updateContent;
        }

        public int getUpdateType() {
            return updateType;
        }

        public void setUpdateType(int updateType) {
            this.updateType = updateType;
        }
    }
}

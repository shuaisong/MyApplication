package com.example.lenovo.myapplication.bean;

import java.util.List;

/**
 * Created by lenovo on 2018/4/11.
 */

public class BaseUrl {

    /**
     * msg : 成功
     * code : 200
     * dataObj : {"otherInfo":{"iosCommentGuidFreqType":1,"iosCommentGuidFreq":7},"noticeInfo":{"noticeList":[]},"browseInfo":{"maxVideoId":3642,"maxArticleId":3876},"urlInfo":{"netCheckUrl":"http://only-380473-112-17-238-21.nstool.netease.com/","picUrlPrefix":"http://img10.mm798.net","videoSearchUrlprefix":"http://prt.mm798.net/search/video/","picThumbUrlprefix":"http://prt.mm798.net:8080/collection","picSearchUrlprefix":"http://prt.mm798.net:8080/search","picSmallUrlprefix":"http://prt.mm798.net:8080/cover","videoThumbUrlprefix":"http://prt.mm798.net/collection/video/","backUrlprefix":"http://pr.mm798.net/back","videoUrlPrefix":"http://prt.mm798.net/videocover","labelUrlprefix":"http://pr.mm798.net/back"},"advertisementInfo":{"picSwitchAdFreq":10,"advertisementList":[],"advertisementPropList":[{"sg_prop":0,"self_prop":0,"tx_prop":10000,"id":1},{"sg_prop":0,"self_prop":0,"tx_prop":10000,"id":2},{"sg_prop":0,"self_prop":0,"tx_prop":10000,"id":3},{"sg_prop":0,"self_prop":0,"tx_prop":10000,"id":4},{"sg_prop":0,"self_prop":0,"tx_prop":10000,"id":5},{"sg_prop":5000,"self_prop":0,"tx_prop":5000,"id":6},{"sg_prop":2000,"self_prop":0,"tx_prop":8000,"id":7},{"sg_prop":2000,"self_prop":0,"tx_prop":8000,"id":8},{"sg_prop":0,"self_prop":0,"tx_prop":10000,"id":9},{"sg_prop":0,"self_prop":0,"tx_prop":10000,"id":10},{"sg_prop":0,"self_prop":0,"tx_prop":10000,"id":11},{"sg_prop":0,"self_prop":0,"tx_prop":10000,"id":12},{"sg_prop":0,"self_prop":0,"tx_prop":10000,"id":13},{"sg_prop":0,"self_prop":0,"tx_prop":10000,"id":14},{"sg_prop":0,"self_prop":0,"tx_prop":10000,"id":15},{"sg_prop":0,"self_prop":10000,"tx_prop":0,"id":16},{"sg_prop":0,"self_prop":10000,"tx_prop":0,"id":17}]}}
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
         * otherInfo : {"iosCommentGuidFreqType":1,"iosCommentGuidFreq":7}
         * noticeInfo : {"noticeList":[]}
         * browseInfo : {"maxVideoId":3642,"maxArticleId":3876}
         * urlInfo : {"netCheckUrl":"http://only-380473-112-17-238-21.nstool.netease.com/","picUrlPrefix":"http://img10.mm798.net","videoSearchUrlprefix":"http://prt.mm798.net/search/video/","picThumbUrlprefix":"http://prt.mm798.net:8080/collection","picSearchUrlprefix":"http://prt.mm798.net:8080/search","picSmallUrlprefix":"http://prt.mm798.net:8080/cover","videoThumbUrlprefix":"http://prt.mm798.net/collection/video/","backUrlprefix":"http://pr.mm798.net/back","videoUrlPrefix":"http://prt.mm798.net/videocover","labelUrlprefix":"http://pr.mm798.net/back"}
         * advertisementInfo : {"picSwitchAdFreq":10,"advertisementList":[],"advertisementPropList":[{"sg_prop":0,"self_prop":0,"tx_prop":10000,"id":1},{"sg_prop":0,"self_prop":0,"tx_prop":10000,"id":2},{"sg_prop":0,"self_prop":0,"tx_prop":10000,"id":3},{"sg_prop":0,"self_prop":0,"tx_prop":10000,"id":4},{"sg_prop":0,"self_prop":0,"tx_prop":10000,"id":5},{"sg_prop":5000,"self_prop":0,"tx_prop":5000,"id":6},{"sg_prop":2000,"self_prop":0,"tx_prop":8000,"id":7},{"sg_prop":2000,"self_prop":0,"tx_prop":8000,"id":8},{"sg_prop":0,"self_prop":0,"tx_prop":10000,"id":9},{"sg_prop":0,"self_prop":0,"tx_prop":10000,"id":10},{"sg_prop":0,"self_prop":0,"tx_prop":10000,"id":11},{"sg_prop":0,"self_prop":0,"tx_prop":10000,"id":12},{"sg_prop":0,"self_prop":0,"tx_prop":10000,"id":13},{"sg_prop":0,"self_prop":0,"tx_prop":10000,"id":14},{"sg_prop":0,"self_prop":0,"tx_prop":10000,"id":15},{"sg_prop":0,"self_prop":10000,"tx_prop":0,"id":16},{"sg_prop":0,"self_prop":10000,"tx_prop":0,"id":17}]}
         */

        private OtherInfoBean otherInfo;
        private NoticeInfoBean noticeInfo;
        private BrowseInfoBean browseInfo;
        private UrlInfoBean urlInfo;
        private AdvertisementInfoBean advertisementInfo;

        public OtherInfoBean getOtherInfo() {
            return otherInfo;
        }

        public void setOtherInfo(OtherInfoBean otherInfo) {
            this.otherInfo = otherInfo;
        }

        public NoticeInfoBean getNoticeInfo() {
            return noticeInfo;
        }

        public void setNoticeInfo(NoticeInfoBean noticeInfo) {
            this.noticeInfo = noticeInfo;
        }

        public BrowseInfoBean getBrowseInfo() {
            return browseInfo;
        }

        public void setBrowseInfo(BrowseInfoBean browseInfo) {
            this.browseInfo = browseInfo;
        }

        public UrlInfoBean getUrlInfo() {
            return urlInfo;
        }

        public void setUrlInfo(UrlInfoBean urlInfo) {
            this.urlInfo = urlInfo;
        }

        public AdvertisementInfoBean getAdvertisementInfo() {
            return advertisementInfo;
        }

        public void setAdvertisementInfo(AdvertisementInfoBean advertisementInfo) {
            this.advertisementInfo = advertisementInfo;
        }

        public static class OtherInfoBean {
            /**
             * iosCommentGuidFreqType : 1
             * iosCommentGuidFreq : 7
             */

            private int iosCommentGuidFreqType;
            private int iosCommentGuidFreq;

            public int getIosCommentGuidFreqType() {
                return iosCommentGuidFreqType;
            }

            public void setIosCommentGuidFreqType(int iosCommentGuidFreqType) {
                this.iosCommentGuidFreqType = iosCommentGuidFreqType;
            }

            public int getIosCommentGuidFreq() {
                return iosCommentGuidFreq;
            }

            public void setIosCommentGuidFreq(int iosCommentGuidFreq) {
                this.iosCommentGuidFreq = iosCommentGuidFreq;
            }
        }

        public static class NoticeInfoBean {
            private List<?> noticeList;

            public List<?> getNoticeList() {
                return noticeList;
            }

            public void setNoticeList(List<?> noticeList) {
                this.noticeList = noticeList;
            }
        }

        public static class BrowseInfoBean {
            /**
             * maxVideoId : 3642
             * maxArticleId : 3876
             */

            private int maxVideoId;
            private int maxArticleId;

            public int getMaxVideoId() {
                return maxVideoId;
            }

            public void setMaxVideoId(int maxVideoId) {
                this.maxVideoId = maxVideoId;
            }

            public int getMaxArticleId() {
                return maxArticleId;
            }

            public void setMaxArticleId(int maxArticleId) {
                this.maxArticleId = maxArticleId;
            }
        }

        public static class UrlInfoBean {
            /**
             * netCheckUrl : http://only-380473-112-17-238-21.nstool.netease.com/
             * picUrlPrefix : http://img10.mm798.net
             * videoSearchUrlprefix : http://prt.mm798.net/search/video/
             * picThumbUrlprefix : http://prt.mm798.net:8080/collection
             * picSearchUrlprefix : http://prt.mm798.net:8080/search
             * picSmallUrlprefix : http://prt.mm798.net:8080/cover
             * videoThumbUrlprefix : http://prt.mm798.net/collection/video/
             * backUrlprefix : http://pr.mm798.net/back
             * videoUrlPrefix : http://prt.mm798.net/videocover
             * labelUrlprefix : http://pr.mm798.net/back
             */

            private String netCheckUrl;
            private String picUrlPrefix;
            private String videoSearchUrlprefix;
            private String picThumbUrlprefix;
            private String picSearchUrlprefix;
            private String picSmallUrlprefix;
            private String videoThumbUrlprefix;
            private String backUrlprefix;
            private String videoUrlPrefix;
            private String labelUrlprefix;

            public String getNetCheckUrl() {
                return netCheckUrl;
            }

            public void setNetCheckUrl(String netCheckUrl) {
                this.netCheckUrl = netCheckUrl;
            }

            public String getPicUrlPrefix() {
                return picUrlPrefix;
            }

            public void setPicUrlPrefix(String picUrlPrefix) {
                this.picUrlPrefix = picUrlPrefix;
            }

            public String getVideoSearchUrlprefix() {
                return videoSearchUrlprefix;
            }

            public void setVideoSearchUrlprefix(String videoSearchUrlprefix) {
                this.videoSearchUrlprefix = videoSearchUrlprefix;
            }

            public String getPicThumbUrlprefix() {
                return picThumbUrlprefix;
            }

            public void setPicThumbUrlprefix(String picThumbUrlprefix) {
                this.picThumbUrlprefix = picThumbUrlprefix;
            }

            public String getPicSearchUrlprefix() {
                return picSearchUrlprefix;
            }

            public void setPicSearchUrlprefix(String picSearchUrlprefix) {
                this.picSearchUrlprefix = picSearchUrlprefix;
            }

            public String getPicSmallUrlprefix() {
                return picSmallUrlprefix;
            }

            public void setPicSmallUrlprefix(String picSmallUrlprefix) {
                this.picSmallUrlprefix = picSmallUrlprefix;
            }

            public String getVideoThumbUrlprefix() {
                return videoThumbUrlprefix;
            }

            public void setVideoThumbUrlprefix(String videoThumbUrlprefix) {
                this.videoThumbUrlprefix = videoThumbUrlprefix;
            }

            public String getBackUrlprefix() {
                return backUrlprefix;
            }

            public void setBackUrlprefix(String backUrlprefix) {
                this.backUrlprefix = backUrlprefix;
            }

            public String getVideoUrlPrefix() {
                return videoUrlPrefix;
            }

            public void setVideoUrlPrefix(String videoUrlPrefix) {
                this.videoUrlPrefix = videoUrlPrefix;
            }

            public String getLabelUrlprefix() {
                return labelUrlprefix;
            }

            public void setLabelUrlprefix(String labelUrlprefix) {
                this.labelUrlprefix = labelUrlprefix;
            }
        }

        public static class AdvertisementInfoBean {
            /**
             * picSwitchAdFreq : 10
             * advertisementList : []
             * advertisementPropList : [{"sg_prop":0,"self_prop":0,"tx_prop":10000,"id":1},{"sg_prop":0,"self_prop":0,"tx_prop":10000,"id":2},{"sg_prop":0,"self_prop":0,"tx_prop":10000,"id":3},{"sg_prop":0,"self_prop":0,"tx_prop":10000,"id":4},{"sg_prop":0,"self_prop":0,"tx_prop":10000,"id":5},{"sg_prop":5000,"self_prop":0,"tx_prop":5000,"id":6},{"sg_prop":2000,"self_prop":0,"tx_prop":8000,"id":7},{"sg_prop":2000,"self_prop":0,"tx_prop":8000,"id":8},{"sg_prop":0,"self_prop":0,"tx_prop":10000,"id":9},{"sg_prop":0,"self_prop":0,"tx_prop":10000,"id":10},{"sg_prop":0,"self_prop":0,"tx_prop":10000,"id":11},{"sg_prop":0,"self_prop":0,"tx_prop":10000,"id":12},{"sg_prop":0,"self_prop":0,"tx_prop":10000,"id":13},{"sg_prop":0,"self_prop":0,"tx_prop":10000,"id":14},{"sg_prop":0,"self_prop":0,"tx_prop":10000,"id":15},{"sg_prop":0,"self_prop":10000,"tx_prop":0,"id":16},{"sg_prop":0,"self_prop":10000,"tx_prop":0,"id":17}]
             */

            private int picSwitchAdFreq;
            private List<?> advertisementList;
            private List<AdvertisementPropListBean> advertisementPropList;

            public int getPicSwitchAdFreq() {
                return picSwitchAdFreq;
            }

            public void setPicSwitchAdFreq(int picSwitchAdFreq) {
                this.picSwitchAdFreq = picSwitchAdFreq;
            }

            public List<?> getAdvertisementList() {
                return advertisementList;
            }

            public void setAdvertisementList(List<?> advertisementList) {
                this.advertisementList = advertisementList;
            }

            public List<AdvertisementPropListBean> getAdvertisementPropList() {
                return advertisementPropList;
            }

            public void setAdvertisementPropList(List<AdvertisementPropListBean> advertisementPropList) {
                this.advertisementPropList = advertisementPropList;
            }

            public static class AdvertisementPropListBean {
                /**
                 * sg_prop : 0
                 * self_prop : 0
                 * tx_prop : 10000
                 * id : 1
                 */

                private int sg_prop;
                private int self_prop;
                private int tx_prop;
                private int id;

                public int getSg_prop() {
                    return sg_prop;
                }

                public void setSg_prop(int sg_prop) {
                    this.sg_prop = sg_prop;
                }

                public int getSelf_prop() {
                    return self_prop;
                }

                public void setSelf_prop(int self_prop) {
                    this.self_prop = self_prop;
                }

                public int getTx_prop() {
                    return tx_prop;
                }

                public void setTx_prop(int tx_prop) {
                    this.tx_prop = tx_prop;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }
            }
        }
    }
}

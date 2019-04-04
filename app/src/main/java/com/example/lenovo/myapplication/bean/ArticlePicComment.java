package com.example.lenovo.myapplication.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lenovo on 2018/8/12.
 * auther:lenovo
 * Date：2018/8/12
 */

public class ArticlePicComment implements Serializable {

    /**
     * msg : 成功
     * code : 200
     * dataObj : {"articleCommentList":[{"gmt_create":1534686061000,"uid":12249152812,"isMem":1,"user_nickname":"自然","content":"气质!","user_avatar_url":"http://wx.qlogo.cn/mmopen/vi_32/BwF8yMFrxeiaibG71NmvpTBMnrXOwicTnKf42Z4Cw4gn4UL9yPAkNpELSSIibyKxdYicqEVtVCTbcqmjkpauicnEZjTA/0"},{"gmt_create":1534268798000,"uid":15183106242,"isMem":1,"user_nickname":"纱雾小可爱","content":"三年血赚","user_avatar_url":"http://thirdqq.qlogo.cn/qqapp/101411583/144A5140681DA27FD73854F6BD1CAA88/100"},{"gmt_create":1534239993000,"uid":19163197442,"isMem":1,"user_nickname":"伪装:-D","content":"好漂亮妹子","user_avatar_url":"http://thirdqq.qlogo.cn/qqapp/101411583/7B63E041B6B046DB612E70762384DD53/100"},{"gmt_create":1534231807000,"uid":13959746562,"isMem":1,"user_nickname":"璀璨☞LY-R☜!","content":"王雨纯真的很美，至少我认为真的美，如果走明星路线绝对可以，不过现在却成了多少人\u2026\u2026的对象！觉得挺可惜的，不过每个人有每个人的追求吧～","user_avatar_url":"http://q.qlogo.cn/qqapp/101411583/F40FC4B3E011259909984C757DCB17D0/100"},{"gmt_create":1534146312000,"uid":12228052132,"isMem":1,"user_nickname":"小乖乖莪暧祢","content":"就是牙齿很好那个妹子！嗯！不错！","user_avatar_url":"http://q.qlogo.cn/qqapp/101411583/D32569E7BED58CEDC60D8D14C5473420/100"},{"gmt_create":1534139592000,"uid":12884076962,"isMem":1,"user_nickname":"W","content":"这是你的新内衣吗？","user_avatar_url":"http://pr.mm798.net/avatar/12884076962_1519743040_38586E02CA3DC1C019FDB54C349D8695"},{"gmt_create":1534058368000,"uid":12227740102,"isMem":1,"user_nickname":"她能守护亦能暖心","content":"毫无抵抗力","user_avatar_url":"http://q.qlogo.cn/qqapp/101411583/35F73F221FDB7512D1B18E5932B5FCAA/100"},{"gmt_create":1534056972000,"uid":17725836162,"isMem":1,"user_nickname":"椅楼听枫雨","content":"亲亲","user_avatar_url":"http://thirdqq.qlogo.cn/qqapp/101411583/C7537FA625F5F51C1CCE976A3BADF96E/100"},{"gmt_create":1534055665000,"uid":12248948052,"isMem":1,"user_nickname":"甜软奶喘♥","content":"有没有撩污的小哥哥","user_avatar_url":"http://pr.mm798.net/avatar/12248948052_1532676270_91F48B69FB2710D2B38639D58C7E8B72"},{"gmt_create":1534051318000,"uid":12248661982,"isMem":1,"user_nickname":"简简单单、","content":"最近胖了不少，更性感了，","user_avatar_url":"http://pr.mm798.net/avatar/12248661982_1516457280_3EFADCC808F288DD664CAF2AD56904E2"},{"gmt_create":1534049638000,"uid":18521758732,"isMem":1,"user_nickname":"老婆","content":"22张好漂亮","user_avatar_url":"http://pr.mm798.net/avatar/18521758732_1534049386_14A935D6B81B3A51EAD6C44D6A62DB23"},{"gmt_create":1534027995000,"uid":19074387842,"isMem":1,"user_nickname":"新用户","content":"身材一流美","user_avatar_url":"http://pr.mm798.net/avatar/avatar_default_2_0_0.png"},{"gmt_create":1534018518000,"uid":15508951042,"isMem":1,"user_nickname":"新用珊瑚海","content":"我喜欢有肉的她，太舒服了爱死你了","user_avatar_url":"http://pr.mm798.net/avatar/avatar_default_2_0_0.png"},{"gmt_create":1533989297000,"uid":13114747842,"isMem":1,"user_nickname":"新用户","content":"怎么那么美？","user_avatar_url":"http://pr.mm798.net/avatar/avatar_default_2_0_0.png"},{"gmt_create":1533987168000,"uid":19082310402,"isMem":1,"user_nickname":"我不是蝴蝶","content":"6","user_avatar_url":"http://thirdqq.qlogo.cn/qqapp/101411583/E0E39405DDA700C99B7FB9DCC4242ACF/100"},{"gmt_create":1533986608000,"uid":13545806402,"isMem":1,"user_nickname":"大王","content":"好漂亮。","user_avatar_url":"http://pr.mm798.net/avatar/avatar_default_2_0_0.png"},{"gmt_create":1533980549000,"uid":14317966092,"isMem":1,"user_nickname":"赵小米我爱你","content":"好美","user_avatar_url":"http://pr.mm798.net/avatar/avatar_default_2_0_0.png"},{"gmt_create":1533971847000,"uid":17907453122,"isMem":1,"user_nickname":"伤口不在愈合","content":"完美","user_avatar_url":"http://thirdqq.qlogo.cn/qqapp/101411583/F477941444B5EDED55457A7774A414DF/100"},{"gmt_create":1533968907000,"uid":19076301772,"isMem":1,"user_nickname":"西北望","content":"15好销魂，自摸","user_avatar_url":"http://pr.mm798.net/avatar/avatar_default_2_0_0.png"},{"gmt_create":1533966449000,"uid":12226486562,"isMem":1,"user_nickname":"吃吧吃吧吃吧","content":"李思宁最美","user_avatar_url":"http://mmvd.sfaa.tv/imgs/mm131/sys/avatar/avatar_default_1_0_4.png"}]}
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
        private List<ArticleCommentListBean> articleCommentList;

        public List<ArticleCommentListBean> getArticleCommentList() {
            return articleCommentList;
        }

        public void setArticleCommentList(List<ArticleCommentListBean> articleCommentList) {
            this.articleCommentList = articleCommentList;
        }

        public static class ArticleCommentListBean {
            /**
             * gmt_create : 1534686061000
             * uid : 12249152812
             * isMem : 1
             * user_nickname : 自然
             * content : 气质!
             * user_avatar_url : http://wx.qlogo.cn/mmopen/vi_32/BwF8yMFrxeiaibG71NmvpTBMnrXOwicTnKf42Z4Cw4gn4UL9yPAkNpELSSIibyKxdYicqEVtVCTbcqmjkpauicnEZjTA/0
             */

            private long gmt_create;
            private long uid;
            private int isMem;
            private String user_nickname;
            private String content;
            private String user_avatar_url;

            public long getGmt_create() {
                return gmt_create;
            }

            public void setGmt_create(long gmt_create) {
                this.gmt_create = gmt_create;
            }

            public long getUid() {
                return uid;
            }

            public void setUid(long uid) {
                this.uid = uid;
            }

            public int getIsMem() {
                return isMem;
            }

            public void setIsMem(int isMem) {
                this.isMem = isMem;
            }

            public String getUser_nickname() {
                return user_nickname;
            }

            public void setUser_nickname(String user_nickname) {
                this.user_nickname = user_nickname;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getUser_avatar_url() {
                return user_avatar_url;
            }

            public void setUser_avatar_url(String user_avatar_url) {
                this.user_avatar_url = user_avatar_url;
            }
        }
    }
}

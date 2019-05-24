package com.example.lenovo.myapplication;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2018/8/11.
 * auther:lenovo
 * Date：2018/8/11
 */

public class Contant {
    public static String baseUrl = "http://api.mm131.tv:88/mm131/";
    public static final int NEW = 0;
    public static final int HOT = 1;
    public static final String APID = "apid";
    public static final String content = "content";
    public static final String sign = "sign";
    public static final String token = "token";
    public static final String tokenValue = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjE5MDg5Mjk2OTYxLCJyYW5kb20iOjE1MzQwMDg5OTQ3MDksImxvZ2luX3R5cGUiOjF9.zn3hDDh-UCumh8Pvk7Klg1VL-dw8dneUtYY3aGGhKUY";

    public static final String initData = "initData";
    public static final String initDataSign = "A804EC79FEB091845E05FBE4BFAFEF63";

    public static final String checkVersion = "checkVersion";
    public static final String versionSign = "C7385B6895EAE9602050567ABE8BBFE0";

    public static final String feedbackStatus = "feedbackStatus";
    public static final String feedbacksign = "A804EC79FEB091845E05FBE4BFAFEF63";

    public static final String getRecommendLabelList21 = "getRecommendLabelList21";
    public static final String recommendsign = "A804EC79FEB091845E05FBE4BFAFEF63";

    public static final String getSearchKeywordsList21 = "getSearchKeywordsList21";
    public static final String SearchSign1 = "68DD431B93DE5631261FAA67B196E341";
    public static final String SearchSign2 = "0E43B21D4E160A3ECCA9CDBD955EC428";

    public static final String getArticlePicDetail = "getArticlePicDetail";

    public static final String getArticleComment = "getArticleComment";

    public static final String getHotPicList = "getHotPicList";
    public static final String getNewPicList = "getNewPicList";
    public static final String imgsign = "BCBEC9182B289F9A0D7E1A9761B6ACD3";
    public static final String getVideos = "getNewVideoList";
    public static final String videosign = "BCBEC9182B289F9A0D7E1A9761B6ACD3";
    public static final String getCollectionList = "getCollectionList";
    public static final String CollectionListSign = "924FD6217E23E2F12F6D169AEBA5E64E";
    public static final String getArticleVideoDetail = "getArticleVideoDetail";
    public static final String getArticleVideoDetailSign = "BF95487749F88DDD039EB9AD43CBDD76";
    public static final String getRecommandVideoList = "getRecommandVideoList";
    public static final String getRecommandVideoListSign = "A804EC79FEB091845E05FBE4BFAFEF63";
    public static final String labelContent21 = "labelContent21";
    public static final String labelContent1Sign = "56C18C0E63743DEB29C8E4517FD0FC40";
    public static final String labelContent2Sign = "DB1682D24C95AF252146262476A17AD4";
    public static final String searchContent21 = "searchContent21";
    public static final String searchContent1Sign = "D0EB164C0AFEF2BAD72A61DDFA9F170A";
    public static final String searchContent2Sign = "A13A6E3DF7C3720560E503205266D47E";
    public static final String getLabelList = "getLabelList";
    public static final String getLabelList1Sign = "239C34437747D54232F1C0AB50ED2827";
    public static final String getLabelList2Sign = "3781FED0203C2F8B311F8FE1E8FFC92F";

    public static Map<String, String[]> getHotSampleList() {
        String[] lastIndex = {"410", "430", "450", "470", "490", "510"};
        String[] signs = {"C091655F49CD2E1CAD73EB8C26F5A82A", "3EB9F384ACC65B6E267B10FF93AEA7D5",
                "CF920455BF62E8BF47E4287DAF695291", "C70D946033E4C13D0865B919D0972A7A",
                "92530CF7861A081C17F3A1AE2DC8B701", "CBDE1270010325942575F6F0D987EDB0"};
        Map<String, String[]> mMap = new HashMap<>();
        mMap.put("lastIndex", lastIndex);
        mMap.put("signs", signs);
        return mMap;
    }

    public static Map<String, String[]> getNewSampleList() {
        String[] lastIndex = {"4238", "4218", "4199", "4179", "4159", "4139", "4119", "4099"};
        String[] signs = {"91002B49954B5FDCA459E42828D970CB", "5A2CE147E43BE037B7C86D3AEE714191",
                "B847EB77F37A0892543252D2594E4B77", "5813A812D97C6746DA7EF1CB30CC5540",
                "C55DA7DAF0F67281AD6F50B663EFF021", "E2A1DA938D914DB47E6F686DA310E567",
                "6C765BFF9512E95F50C171ECFFCDE430", "17D26C0404C0212162DB7F06E33A3803"
        };
        Map<String, String[]> mMap = new HashMap<>();
        mMap.put("lastIndex", lastIndex);
        mMap.put("signs", signs);
        return mMap;
    }

    public static Map<String, String[]> getImgDetailSampleList() {
        String[] apid = {"4244", "4243", "4242", "4241", "4240", "4239"};
        String[] signs = {"8F2A06EC84D4FB4A5F9001D166E885F6", "C9666CA5CDC19054670A638131B58BFC",
                "FEE2DA063B93F8B374FBBF93DC38A9F7", "64AFF5C26B31A95B50048635A2A8AD90",
                "F75A179DCC148597C6A87B2D06F9C643", "C0B141E128D6FC76814B34ADE70471EB"};
        Map<String, String[]> mMap = new HashMap<>();
        mMap.put("apid", apid);
        mMap.put("signs", signs);
        return mMap;
    }

    public static Map<String, String[]> getArticleSampleList() {
        String[] aids = {"4244", "4243", "4242", "4241", "4240", "4239"};
        String[] signs = {"B7583548F622A529EC008D2703440BC8", "2ECF94085490A58D68ECDE8A3588E86C",
                "7608DD03DF293CCD40C901DFCAA663E0", "B77739674B36F83B8E53ECE1FAD386F1",
                "3F3A784746B6E9C2B6AD11C5A9021D7B", "12754F790D9179D22C2D2192D061D0DB"};
        String[] signs2 = {"A817567AA799F336E4D41FDDC8F971A1", "3AD480553C551CD6B231FCD9ED3F4A41",
                "1E3DEF1CC0985B540A46BDEE3E181045", "12073A08A7BB7E2E862360153FE24D87",
                "CE9A0D98CC47802B2F976F5810E649BE", "C2E5F90DC06603C996780D790799819D"};
        Map<String, String[]> mMap = new HashMap<>();
        mMap.put("aid", aids);
        mMap.put("signs", signs);
        mMap.put("signs2", signs2);
        return mMap;
    }
    public static final int DetailImgRequestCode = 101;
    public static final int DetailImgResultCode = 102;

    public static final int load =103;
    public static final int fresh =104;
    public static final int normal =105;
}

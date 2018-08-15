package com.example.lenovo.myapplication;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2018/8/11.
 * auther:lenovo
 * Date：2018/8/11
 */

public class Contant {

    public static final String imgsign = "BCBEC9182B289F9A0D7E1A9761B6ACD3";
    public static String baseUrl = "http://mmapi.yomei.tv/mm131/";
    public static String baseUrl_img = "http://img10.mm798.net";
    public static final int NEW = 0;
    public static final int HOT = 1;
    public static final String content = "content";

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
        String[] lastIndex = {"4199", "4179", "4159", "4139", "4119", "4099"};
        String[] signs = {"B847EB77F37A0892543252D2594E4B77", "5813A812D97C6746DA7EF1CB30CC5540",
                "C55DA7DAF0F67281AD6F50B663EFF021", "E2A1DA938D914DB47E6F686DA310E567",
                "6C765BFF9512E95F50C171ECFFCDE430", "17D26C0404C0212162DB7F06E33A3803"};
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
}

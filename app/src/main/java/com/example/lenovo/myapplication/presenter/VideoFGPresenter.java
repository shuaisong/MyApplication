package com.example.lenovo.myapplication.presenter;

import com.example.lenovo.myapplication.Contact.VideoFGContact;
import com.example.lenovo.myapplication.Contant;
import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.bean.NewVideo;
import com.example.lenovo.myapplication.bean.VideoAdvertisement;
import com.example.lenovo.myapplication.request.CallBack;
import com.example.lenovo.myapplication.utils.LogUtil;
import com.google.gson.Gson;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.mode.CacheMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by lenovo on 2018/8/12.
 * auther:lenovo
 * Date：2018/8/12
 */

public class VideoFGPresenter extends RxPresenter<VideoFGContact.View> implements VideoFGContact.Presenter<VideoFGContact.View> {
    @Inject
    VideoFGPresenter() {
    }

    @Override
    public void getVideos(String sign, String lastIndex) {
         ViseHttp.GET (Contant.getVideos)
                 .tag(this).cacheMode(CacheMode.FIRST_REMOTE)
                 .addHeader(Contant.sign,sign)
                 .addHeader(Contant.token,Contant.tokenValue)
                 .addParam("lastIndex", lastIndex)
         .request(new CallBack<NewVideo>() {
            @Override
            public void onSuccess(NewVideo data) {
                ViseHttp .cancelTag(VideoFGPresenter.this);
                if (data.getCode()==200&&data.getDataObj()!=null){
                    view.showVideos(data.getDataObj());
                }
            }

             @Override
             public void onFail(int errCode, String errMsg) {
                 super.onFail(errCode, errMsg);
                 LogUtil.d(errCode+errMsg);
                 view.showVideos(null);
             }
         });
    }

    @Override
    public void gerAD() {
        String [] titles = {"三国群英录","和老公异地分居最多能忍几年?","YY","UC浏览器","抖音，笑对明天的力量","抖音短视频，好玩超乎你想象","快遇爱"};
        int [] img = {R.mipmap.sanguo,R.mipmap.liyi,R.mipmap.yy,R.mipmap.uc,R.mipmap.douyin,R.mipmap.douyin1,R.mipmap.yuai};
        String string ="{\"ret\":0,\n" +
                "\"rpt\":0,\n" +
                "\"msg\":\"\",\n" +
                "\"reqinterval\":1740,\n" +
                "\"last_ads\":{\"responsed_ad_data\":\"8Q1_qpR5jjA\"}\n" +
                ",\n" +
                "\"data\":{\n" +
                "\"6030720636414512\":{\n" +
                "\"ret\":0,\n" +
                "\"msg\":\"\",\n" +
                "\"list\":[{\"cl\":\"66493943\",\n" +
                "\"img\":\"http://pgdt.ugdtimg.com/gdt/0/DAAfpZmAUAALQABiBbfnDuDWoKHsM0.jpg/0?ck=63cecf6254d3b7f4e5584782f2b80218\",\n" +
                "\"img_s\":\"\",\n" +
                "\"img2\":\"http://pgdt.ugdtimg.com/gdt/0/DAAfpZmAEsAEsAAUBbeofrC6BpaXTP.jpg/0?ck=9926508504e414d9e7cd5ca9c75959cd\",\n" +
                "\"video\":\"\",\n" +
                "\"price\":\"-\",\n" +
                "\"reltarget\":0,\n" +
                "\"last_modify_time\":1535017080,\n" +
                "\"txt\":\"龙耀三国\",\n" +
                "\"desc\":\"成功合成隐藏神将诸葛亮，直接无敌\",\n" +
                "\"corporation_name\":\"上海弘贯网络科技有限公司\",\n" +
                "\"ad_industry_id\":4302,\n" +
                "\"edid\":\"b765850f01c42d7323ae5e5a89e1e65e\",\n" +
                "\"rl\":\"http://c.gdt.qq.com/gdt_mclick.fcg?viewid=65hQBeTPzfLtZPvnsdwhZovhUNrYDzEL0xcFc5!8Q0dGbLCcZOWLviz5Tz3JQgphQF2NPn7BaijnUulIevt_6xw2GSszenduIcw9LMqMSLu_Eby0VvHIwtO4bU8MG3Wkj08ysy2jXchxsiJ2P!qrJenWngwetb3py0f9U7lvIiPVaNUFzxVEW2JHMUhWT6YS5qOE_fMby5bgpsRowNmGntS3FO!HbKQI&jtype=0&i=1&os=2&asi=%7B%22mf%22%3A%22samsung%22%2C%22hw%22%3A%22android_x86%22%2C%22br%22%3A%22samsung%22%7D&xp=1\",\n" +
                "\"targetid\":\"1106930819\",\n" +
                "\"apurl\":\"http://v.gdt.qq.com/gdt_stats.fcg?viewid=65hQBeTPzfLtZPvnsdwhZovhUNrYDzEL0xcFc5!8Q0dGbLCcZOWLviz5Tz3JQgphQF2NPn7BaijnUulIevt_6xw2GSszenduIcw9LMqMSLu_Eby0VvHIwtO4bU8MG3Wkj08ysy2jXchxsiJ2P!qrJenWngwetb3py0f9U7lvIiPVaNUFzxVEW2JHMUhWT6YS5qOE_fMby5bgpsRowNmGntS3FO!HbKQI&i=1&os=2&xp=1\",\n" +
                "\"apptrace\":\"\",\n" +
                "\"appclick\":\"\",\n" +
                "\"traceid\":\"mv4qmyvwilt5m01\",\n" +
                "\"linktype\":0,\n" +
                "\"productid\":\"1106930819\",\n" +
                "\"producttype\":12,\n" +
                "\"mqq_via\":\"YYBH5.STORE.COMMONDETAIL.FEED.ADVERTISE.mv4qmyvwilt5m01\",\n" +
                "\"imagetype\":2,\n" +
                "\"negative_feedback_url\":\"https://nc.gdt.qq.com/gdt_report.fcg?viewid=65hQBeTPzfLtZPvnsdwhZovhUNrYDzEL0xcFc5!8Q0dGbLCcZOWLviz5Tz3JQgphQF2NPn7BaijnUulIevt_6xw2GSszenduIcw9LMqMSLu_Eby0VvHIwtO4bU8MG3Wkj08ysy2jXchxsiJ2P!qrJenWngwetb3py0f9U7lvIiPVaNUFzxVEW2JHMUhWT6YS5qOE_fMby5bgpsRowNmGntS3FO!HbKQI&acttype=__ACT_TYPE__&s=15\",\n" +
                "\"screenshot_url_list\":[\"http://pp.myapp.com/ma_pic2/0/shot_52716718_1_1533007287/550\",\n" +
                "\"http://pp.myapp.com/ma_pic2/0/shot_52716718_2_1533007287/550\",\n" +
                "\"http://pp.myapp.com/ma_pic2/0/shot_52716718_3_1533007287/550\",\n" +
                "\"http://pp.myapp.com/ma_pic2/0/shot_52716718_4_1533007287/550\",\n" +
                "\"http://pp.myapp.com/ma_pic2/0/shot_52716718_5_1533007287/550\"],\n" +
                "\"domain\":\"www.myapp.com\",\n" +
                "\"appcategoryname\":\"角色扮演\",\n" +
                "\"acttype\":1,\n" +
                "\"pic_width\":1280,\n" +
                "\"pic_height\":720,\n" +
                "\"ext\":{\"FSubordinateProductId\":\"0;\",\"alist\":{\"2022\":{\"aid\":\"com.tencent.tmgp.longyaothreeg\",\"atype\":2025},\"2025\":{\"aid\":{\"iconurl\":\"http://pp.myapp.com/ma_icon/0/icon_52716718_1533007289/256\",\"total\":0},\"atype\":2025}},\"appclass\":0,\"appclass3\":0,\"appid\":1106930819,\"appname\":\"龙耀三国\",\"appver\":\"1002\",\"appvername\":\"10.02\",\"creative_finger_print_productid\":\"0\",\"desttype\":0,\"downloadnum\":0,\"mappid\":1106930819,\"outerurl\":\"\",\"pkg_name\":\"com.tencent.tmgp.longyaothreeg\",\"pkgsize\":99356643,\"quality_productid\":\"3498520239\",\"qzoneliked\":0}\n" +
                ",\n" +
                "\"advertiser_id\":8296038,\n" +
                "\"pkg_download_schema\":\"taskApkId=0&packName=com.tencent.tmgp.longyaothreeg&taskAppId=&appId=&via=ANDROIDQQ.FEED.ADVERTISE.mv4qmyvwilt5m01&versionCode=1002&channel=\",\n" +
                "\"cfg\":{\n" +
                "\"pt\":11}\n" +
                "}\n" +
                ",\n" +
                "{\"cl\":\"62826740\",\n" +
                "\"img\":\"http://pgdt.ugdtimg.com/gdt/0/DAAcUMkAUAALQABhBbXrggDsa3q0Np.jpg/0?ck=49512ebb0ca4030e08a5e3536a8c986d\",\n" +
                "\"img_s\":\"\",\n" +
                "\"img2\":\"http://pgdt.ugdtimg.com/gdt/0/DAAcUMkAEsAEsAAsBbXrgjCGED-8Lq.jpg/0?ck=4ff8bf24dcb579391d0d9fda226e2d6a\",\n" +
                "\"video\":\"\",\n" +
                "\"price\":\"-\",\n" +
                "\"reltarget\":1,\n" +
                "\"last_modify_time\":1535017380,\n" +
                "\"txt\":\"YY\",\n" +
                "\"desc\":\"来YY，看漂亮小姐姐直播哦！\",\n" +
                "\"corporation_name\":\"广州华多网络科技有限公司\",\n" +
                "\"ad_industry_id\":4209,\n" +
                "\"edid\":\"b747ba4b18a7e24bc023d55730794357\",\n" +
                "\"rl\":\"http://c.gdt.qq.com/gdt_mclick.fcg?viewid=zGmgX0jzuiztZPvnsdwhZhXcHXrCTr3h2Zf7kZB9ZqT3g5wP3LPcb0f0MzQOzPzu93pSWewNJlg1U7Xisr9P2XmjBPWsd2bTDuSk48XnaBYlElN!3oXuaLef2YHqrUyTK1MPb5bqufAnJdnBse_jstsWenBZzk!cz8uHRBTQ_898auI8_ReQl8KpKUN1Qw5cXjPjcz3wIWfL1ty1npBTkEIMEqQq0nIX&jtype=0&i=1&os=2&asi=%7B%22mf%22%3A%22samsung%22%2C%22hw%22%3A%22android_x86%22%2C%22br%22%3A%22samsung%22%7D&subordinate_product_id=70129492;000116083638353332393037&xp=1\",\n" +
                "\"targetid\":\"100734842\",\n" +
                "\"apurl\":\"http://v.gdt.qq.com/gdt_stats.fcg?viewid=zGmgX0jzuiztZPvnsdwhZhXcHXrCTr3h2Zf7kZB9ZqT3g5wP3LPcb0f0MzQOzPzu93pSWewNJlg1U7Xisr9P2XmjBPWsd2bTDuSk48XnaBYlElN!3oXuaLef2YHqrUyTK1MPb5bqufAnJdnBse_jstsWenBZzk!cz8uHRBTQ_898auI8_ReQl8KpKUN1Qw5cXjPjcz3wIWfL1ty1npBTkEIMEqQq0nIX&i=1&os=2&xp=1\",\n" +
                "\"apptrace\":\"\",\n" +
                "\"appclick\":\"\",\n" +
                "\"traceid\":\"mv4qmyvwilt5m02\",\n" +
                "\"linktype\":0,\n" +
                "\"productid\":\"100734842\",\n" +
                "\"producttype\":12,\n" +
                "\"mqq_via\":\"YYBH5.STORE.COMMONDETAIL.FEED.ADVERTISE.mv4qmyvwilt5m02\",\n" +
                "\"imagetype\":2,\n" +
                "\"negative_feedback_url\":\"https://nc.gdt.qq.com/gdt_report.fcg?viewid=zGmgX0jzuiztZPvnsdwhZhXcHXrCTr3h2Zf7kZB9ZqT3g5wP3LPcb0f0MzQOzPzu93pSWewNJlg1U7Xisr9P2XmjBPWsd2bTDuSk48XnaBYlElN!3oXuaLef2YHqrUyTK1MPb5bqufAnJdnBse_jstsWenBZzk!cz8uHRBTQ_898auI8_ReQl8KpKUN1Qw5cXjPjcz3wIWfL1ty1npBTkEIMEqQq0nIX&acttype=__ACT_TYPE__&s=15\",\n" +
                "\"screenshot_url_list\":[\"http://pp.myapp.com/ma_pic2/0/shot_9652_1_1534495334/550\",\n" +
                "\"http://pp.myapp.com/ma_pic2/0/shot_9652_2_1534495334/550\",\n" +
                "\"http://pp.myapp.com/ma_pic2/0/shot_9652_3_1534495334/550\",\n" +
                "\"http://pp.myapp.com/ma_pic2/0/shot_9652_4_1534495334/550\"],\n" +
                "\"domain\":\"www.myapp.com\",\n" +
                "\"appcategoryname\":\"视频\",\n" +
                "\"acttype\":1,\n" +
                "\"pic_width\":1280,\n" +
                "\"pic_height\":720,\n" +
                "\"ext\":{\"FSubordinateProductId\":\"000116083638353332393037;70129492\",\"alist\":{\"2022\":{\"aid\":\"com.duowan.mobile\",\"atype\":2025},\"2025\":{\"aid\":{\"iconurl\":\"http://pp.myapp.com/ma_icon/0/icon_9652_1534495336/256\",\"total\":149518911},\"atype\":2025}},\"appclass\":0,\"appclass3\":0,\"appid\":100734842,\"appname\":\"YY\",\"appscore\":9,\"appver\":\"65509\",\"appvername\":\"7.9.3\",\"creative_finger_print_productid\":\"0\",\"desttype\":0,\"downloadnum\":149518911,\"mappid\":100734842,\"outerurl\":\"\",\"pkg_name\":\"com.duowan.mobile\",\"pkgsize\":61002141,\"quality_productid\":\"1898160864\",\"qzoneliked\":1,\"subordinate_product_id\":\"70129492;000116083638353332393037\"}\n" +
                ",\n" +
                "\"advertiser_id\":7422756,\n" +
                "\"pkg_download_schema\":\"taskApkId=0&packName=com.duowan.mobile&taskAppId=&appId=&via=ANDROIDQQ.FEED.ADVERTISE.mv4qmyvwilt5m02&versionCode=65509&channel=000116083638353332393037;70129492\",\n" +
                "\"cfg\":{\n" +
                "\"pt\":11}\n" +
                "}\n" +
                ",\n" +
                "{\"cl\":\"65889556\",\n" +
                "\"img\":\"http://pgdt.ugdtimg.com/gdt/0/DAAfp5SAUAALQABjBbenLwDiaHbffq.jpg/0?ck=1803196039a106a7976c96eb94a164d7\",\n" +
                "\"img_s\":\"\",\n" +
                "\"img2\":\"http://pgdt.ugdtimg.com/gdt/0/DAAfp5SAEsAEsAAWBbenMYAzKSeRXE.jpg/0?ck=a9312cabe9e0e5c2425f73926a22547f\",\n" +
                "\"video\":\"\",\n" +
                "\"price\":\"-\",\n" +
                "\"reltarget\":0,\n" +
                "\"last_modify_time\":1534842615,\n" +
                "\"txt\":\"三国群英录\",\n" +
                "\"desc\":\"一定要把华佗升级到最后，实力强的可怕，吊打五虎将\",\n" +
                "\"corporation_name\":\"广东奇趣网络科技有限公司\",\n" +
                "\"ad_industry_id\":4302,\n" +
                "\"edid\":\"78c45f08f8acbad2cf43b51eeaa1d8b4\",\n" +
                "\"rl\":\"http://c.gdt.qq.com/gdt_mclick.fcg?viewid=c8avs1tD!zDtZPvnsdwhZo3Dk1bq2J7rWnQqFpGTb3VGbLCcZOWLviz5Tz3JQgphQF2NPn7BaijnUulIevt_6xw2GSszenduIcw9LMqMSLu_Eby0VvHIwtO4bU8MG3WkLocMwg0I1OFxsiJ2P!qrJenWngwetb3py0f9U7lvIiPVaNUFzxVEWwHEIVjzalL35qOE_fMby5bgpsRowNmGnuCnxsDnvOYR&jtype=0&i=1&os=2&asi=%7B%22mf%22%3A%22samsung%22%2C%22hw%22%3A%22android_x86%22%2C%22br%22%3A%22samsung%22%7D&xp=1\",\n" +
                "\"targetid\":\"1107066508\",\n" +
                "\"apurl\":\"http://v.gdt.qq.com/gdt_stats.fcg?viewid=c8avs1tD!zDtZPvnsdwhZo3Dk1bq2J7rWnQqFpGTb3VGbLCcZOWLviz5Tz3JQgphQF2NPn7BaijnUulIevt_6xw2GSszenduIcw9LMqMSLu_Eby0VvHIwtO4bU8MG3WkLocMwg0I1OFxsiJ2P!qrJenWngwetb3py0f9U7lvIiPVaNUFzxVEWwHEIVjzalL35qOE_fMby5bgpsRowNmGnuCnxsDnvOYR&i=1&os=2&xp=1\",\n" +
                "\"apptrace\":\"\",\n" +
                "\"appclick\":\"\",\n" +
                "\"traceid\":\"mv4qmyvwilt5m03\",\n" +
                "\"linktype\":0,\n" +
                "\"productid\":\"1107066508\",\n" +
                "\"producttype\":12,\n" +
                "\"mqq_via\":\"YYBH5.STORE.COMMONDETAIL.FEED.ADVERTISE.mv4qmyvwilt5m03\",\n" +
                "\"imagetype\":2,\n" +
                "\"negative_feedback_url\":\"https://nc.gdt.qq.com/gdt_report.fcg?viewid=c8avs1tD!zDtZPvnsdwhZo3Dk1bq2J7rWnQqFpGTb3VGbLCcZOWLviz5Tz3JQgphQF2NPn7BaijnUulIevt_6xw2GSszenduIcw9LMqMSLu_Eby0VvHIwtO4bU8MG3WkLocMwg0I1OFxsiJ2P!qrJenWngwetb3py0f9U7lvIiPVaNUFzxVEWwHEIVjzalL35qOE_fMby5bgpsRowNmGnuCnxsDnvOYR&acttype=__ACT_TYPE__&s=15\",\n" +
                "\"screenshot_url_list\":[\"http://pp.myapp.com/ma_pic2/0/shot_52726486_1_1533716036/550\",\n" +
                "\"http://pp.myapp.com/ma_pic2/0/shot_52726486_2_1533716036/550\",\n" +
                "\"http://pp.myapp.com/ma_pic2/0/shot_52726486_3_1533716036/550\",\n" +
                "\"http://pp.myapp.com/ma_pic2/0/shot_52726486_4_1533716036/550\",\n" +
                "\"http://pp.myapp.com/ma_pic2/0/shot_52726486_5_1533716036/550\"],\n" +
                "\"domain\":\"www.myapp.com\",\n" +
                "\"appcategoryname\":\"角色扮演\",\n" +
                "\"acttype\":1,\n" +
                "\"pic_width\":1280,\n" +
                "\"pic_height\":720,\n" +
                "\"ext\":{\"FSubordinateProductId\":\"0;\",\"alist\":{\"2022\":{\"aid\":\"com.tencent.tmgp.sgqyl6kw\",\"atype\":2025},\"2025\":{\"aid\":{\"iconurl\":\"http://pp.myapp.com/ma_icon/0/icon_52726486_1533716044/256\",\"total\":29260},\"atype\":2025}},\"appclass\":0,\"appclass3\":0,\"appid\":1107066508,\"appname\":\"三国群英录\",\"appscore\":9,\"appver\":\"1003\",\"appvername\":\"10.03\",\"creative_finger_print_productid\":\"0\",\"desttype\":0,\"downloadnum\":29260,\"mappid\":1107066508,\"outerurl\":\"\",\"pkg_name\":\"com.tencent.tmgp.sgqyl6kw\",\"pkgsize\":99160275,\"quality_productid\":\"3473876001\",\"qzoneliked\":0}\n" +
                ",\n" +
                "\"advertiser_id\":8298066,\n" +
                "\"pkg_download_schema\":\"taskApkId=0&packName=com.tencent.tmgp.sgqyl6kw&taskAppId=&appId=&via=ANDROIDQQ.FEED.ADVERTISE.mv4qmyvwilt5m03&versionCode=1003&channel=\",\n" +
                "\"cfg\":{\n" +
                "\"pt\":11}\n" +
                "}\n" +
                ",\n" +
                "{\"cl\":\"66204181\",\n" +
                "\"img\":\"http://pgdt.ugdtimg.com/gdt/0/DAAVUSmAUAALQABiBbWDxZBaRl8blQ.jpg/0?ck=bd0d70629a43ff3c2592b70a2e652486\",\n" +
                "\"img_s\":\"\",\n" +
                "\"img2\":\"http://pgdt.ugdtimg.com/gdt/0/DAAVUSmAEsAEsAAwBbWDxcDzJHCtdX.jpg/0?ck=7eaf1bc8a7543346580c3a3a0f9ff46a\",\n" +
                "\"video\":\"\",\n" +
                "\"price\":\"-\",\n" +
                "\"reltarget\":1,\n" +
                "\"last_modify_time\":1534915956,\n" +
                "\"txt\":\"抖音短视频，记录美好生活\",\n" +
                "\"desc\":\"喜欢她？来抖音短视频，随时看她的精彩更新！\",\n" +
                "\"corporation_name\":\"北京派瑞威行广告有限公司\",\n" +
                "\"ad_industry_id\":4204,\n" +
                "\"edid\":\"7b3d8fedfed065a2f817d7f0cfd74afc\",\n" +
                "\"rl\":\"http://c.gdt.qq.com/gdt_mclick.fcg?viewid=65hQBeTPzfLtZPvnsdwhZlyPIJNuWOx4d9C1kL8m4ggVXyjSEnNTrSz5Tz3JQgphQF2NPn7BaijnUulIevt_6xw2GSszenduIcw9LMqMSLu_Eby0VvHIwtO4bU8MG3Wk0QiOeLsi72dxsiJ2P!qrJenWngwetb3py0f9U7lvIiPVaNUFzxVEW5E8___pRe855qOE_fMby5bgpsRowNmGngifCNRR1PSU&jtype=0&i=1&os=2&asi=%7B%22mf%22%3A%22samsung%22%2C%22hw%22%3A%22android_x86%22%2C%22br%22%3A%22samsung%22%7D&subordinate_product_id=70216402;000116083638303535383933&xp=1\",\n" +
                "\"targetid\":\"1105602870\",\n" +
                "\"apurl\":\"http://v.gdt.qq.com/gdt_stats.fcg?viewid=65hQBeTPzfLtZPvnsdwhZlyPIJNuWOx4d9C1kL8m4ggVXyjSEnNTrSz5Tz3JQgphQF2NPn7BaijnUulIevt_6xw2GSszenduIcw9LMqMSLu_Eby0VvHIwtO4bU8MG3Wk0QiOeLsi72dxsiJ2P!qrJenWngwetb3py0f9U7lvIiPVaNUFzxVEW5E8___pRe855qOE_fMby5bgpsRowNmGngifCNRR1PSU&i=1&os=2&xp=1\",\n" +
                "\"apptrace\":\"\",\n" +
                "\"appclick\":\"\",\n" +
                "\"traceid\":\"mv4qmyvwilt5m04\",\n" +
                "\"linktype\":0,\n" +
                "\"productid\":\"1105602870\",\n" +
                "\"producttype\":12,\n" +
                "\"mqq_via\":\"YYBH5.STORE.COMMONDETAIL.FEED.ADVERTISE.mv4qmyvwilt5m04\",\n" +
                "\"imagetype\":2,\n" +
                "\"negative_feedback_url\":\"https://nc.gdt.qq.com/gdt_report.fcg?viewid=65hQBeTPzfLtZPvnsdwhZlyPIJNuWOx4d9C1kL8m4ggVXyjSEnNTrSz5Tz3JQgphQF2NPn7BaijnUulIevt_6xw2GSszenduIcw9LMqMSLu_Eby0VvHIwtO4bU8MG3Wk0QiOeLsi72dxsiJ2P!qrJenWngwetb3py0f9U7lvIiPVaNUFzxVEW5E8___pRe855qOE_fMby5bgpsRowNmGngifCNRR1PSU&acttype=__ACT_TYPE__&s=15\",\n" +
                "\"screenshot_url_list\":[\"http://pp.myapp.com/ma_pic2/0/shot_42350811_1_1534845323/550\",\n" +
                "\"http://pp.myapp.com/ma_pic2/0/shot_42350811_2_1534845323/550\",\n" +
                "\"http://pp.myapp.com/ma_pic2/0/shot_42350811_3_1534845323/550\",\n" +
                "\"http://pp.myapp.com/ma_pic2/0/shot_42350811_4_1534845323/550\"],\n" +
                "\"domain\":\"www.myapp.com\",\n" +
                "\"appcategoryname\":\"视频\",\n" +
                "\"acttype\":1,\n" +
                "\"pic_width\":1280,\n" +
                "\"pic_height\":720,\n" +
                "\"ext\":{\"FSubordinateProductId\":\"000116083638303535383933;70216402\",\"alist\":{\"2022\":{\"aid\":\"com.ss.android.ugc.aweme\",\"atype\":2025},\"2025\":{\"aid\":{\"iconurl\":\"http://pp.myapp.com/ma_icon/0/icon_42350811_1534845325/256\",\"total\":294165371},\"atype\":2025}},\"appclass\":0,\"appclass3\":0,\"appid\":1105602870,\"appname\":\"抖音短视频\",\"appscore\":8,\"appver\":\"250\",\"appvername\":\"2.5.0\",\"creative_finger_print_productid\":\"0\",\"desttype\":0,\"downloadnum\":294165371,\"mappid\":1105602870,\"outerurl\":\"\",\"pkg_name\":\"com.ss.android.ugc.aweme\",\"pkgsize\":11851890,\"quality_productid\":\"1405640696\",\"qzoneliked\":1,\"subordinate_product_id\":\"70216402;000116083638303535383933\"}\n" +
                ",\n" +
                "\"advertiser_id\":5588134,\n" +
                "\"pkg_download_schema\":\"taskApkId=0&packName=com.ss.android.ugc.aweme&taskAppId=&appId=&via=ANDROIDQQ.FEED.ADVERTISE.mv4qmyvwilt5m04&versionCode=250&channel=000116083638303535383933;70216402\",\n" +
                "\"cfg\":{\n" +
                "\"pt\":11}\n" +
                "}\n" +
                ",\n" +
                "{\"cl\":\"64266279\",\n" +
                "\"img\":\"http://pgdt.ugdtimg.com/gdt/0/DAAcT8OAUAALQABVBba9AECoR-KfxX.jpg/0?ck=f768d83af0388c5fce6021804994264a\",\n" +
                "\"img_s\":\"\",\n" +
                "\"img2\":\"http://pgdt.ugdtimg.com/gdt/0/DAAcT8OAEsAEsAAeBbW_VYBRjYkZOc.jpg/0?ck=b401dff3d602a44c228a016b77e143c3\",\n" +
                "\"video\":\"\",\n" +
                "\"price\":\"-\",\n" +
                "\"reltarget\":1,\n" +
                "\"last_modify_time\":1535047347,\n" +
                "\"txt\":\"小姐姐自拍视频免费看\",\n" +
                "\"desc\":\"各种有趣视频，看它就够了！\",\n" +
                "\"corporation_name\":\"百度在线网络技术（北京）有限公司\",\n" +
                "\"ad_industry_id\":4204,\n" +
                "\"edid\":\"730284682d2ac7c3c4f0e6c7952e28c9\",\n" +
                "\"rl\":\"http://c.gdt.qq.com/gdt_mclick.fcg?viewid=DLPszWRLSUPtZPvnsdwhZmP1PFzC!3P_nv__5NZ5RvWBsb_TskEsbIgFbbzxSTegKhmEREQriKVVoklGVK1dURg0!6L75fhqhfA6koP4K8F15jRbGwI3bVvTpnoeyghRMbA9Ji7!E6MyE6ELDgqgZS1T16CaoSoU3JyLzW8s0qYgWy0FbkImUc1TTNPJ3FbI33duuNuZ!5ZcFcNY6pHYGibZH0EcL4wN&jtype=0&i=1&os=2&asi=%7B%22mf%22%3A%22samsung%22%2C%22hw%22%3A%22android_x86%22%2C%22br%22%3A%22samsung%22%7D&subordinate_product_id=70267529;000116083638313731383039&xp=1\",\n" +
                "\"targetid\":\"1105219107\",\n" +
                "\"apurl\":\"http://v.gdt.qq.com/gdt_stats.fcg?viewid=DLPszWRLSUPtZPvnsdwhZmP1PFzC!3P_nv__5NZ5RvWBsb_TskEsbIgFbbzxSTegKhmEREQriKVVoklGVK1dURg0!6L75fhqhfA6koP4K8F15jRbGwI3bVvTpnoeyghRMbA9Ji7!E6MyE6ELDgqgZS1T16CaoSoU3JyLzW8s0qYgWy0FbkImUc1TTNPJ3FbI33duuNuZ!5ZcFcNY6pHYGibZH0EcL4wN&i=1&os=2&xp=1\",\n" +
                "\"apptrace\":\"\",\n" +
                "\"appclick\":\"\",\n" +
                "\"traceid\":\"mv4qmyvwilt5m05\",\n" +
                "\"linktype\":0,\n" +
                "\"productid\":\"1105219107\",\n" +
                "\"producttype\":12,\n" +
                "\"mqq_via\":\"YYBH5.STORE.COMMONDETAIL.FEED.ADVERTISE.mv4qmyvwilt5m05\",\n" +
                "\"imagetype\":2,\n" +
                "\"negative_feedback_url\":\"https://nc.gdt.qq.com/gdt_report.fcg?viewid=DLPszWRLSUPtZPvnsdwhZmP1PFzC!3P_nv__5NZ5RvWBsb_TskEsbIgFbbzxSTegKhmEREQriKVVoklGVK1dURg0!6L75fhqhfA6koP4K8F15jRbGwI3bVvTpnoeyghRMbA9Ji7!E6MyE6ELDgqgZS1T16CaoSoU3JyLzW8s0qYgWy0FbkImUc1TTNPJ3FbI33duuNuZ!5ZcFcNY6pHYGibZH0EcL4wN&acttype=__ACT_TYPE__&s=15\",\n" +
                "\"screenshot_url_list\":[\"http://pp.myapp.com/ma_pic2/0/shot_42262294_1_1535092298/550\",\n" +
                "\"http://pp.myapp.com/ma_pic2/0/shot_42262294_2_1535092298/550\",\n" +
                "\"http://pp.myapp.com/ma_pic2/0/shot_42262294_3_1535092298/550\",\n" +
                "\"http://pp.myapp.com/ma_pic2/0/shot_42262294_4_1535092298/550\",\n" +
                "\"http://pp.myapp.com/ma_pic2/0/shot_42262294_5_1535092298/550\"],\n" +
                "\"domain\":\"www.myapp.com\",\n" +
                "\"appcategoryname\":\"视频\",\n" +
                "\"acttype\":1,\n" +
                "\"pic_width\":1280,\n" +
                "\"pic_height\":720,\n" +
                "\"ext\":{\"FSubordinateProductId\":\"000116083638313731383039;70267529\",\"alist\":{\"2022\":{\"aid\":\"com.baidu.haokan\",\"atype\":2025},\"2025\":{\"aid\":{\"iconurl\":\"http://pp.myapp.com/ma_icon/0/icon_42262294_1535092299/256\",\"total\":38984864},\"atype\":2025}},\"appclass\":0,\"appclass3\":0,\"appid\":1105219107,\"appname\":\"好看视频\",\"appscore\":10,\"appver\":\"205\",\"appvername\":\"4.2.1.10\",\"creative_finger_print_productid\":\"0\",\"desttype\":0,\"downloadnum\":38984864,\"mappid\":1105219107,\"outerurl\":\"\",\"pkg_name\":\"com.baidu.haokan\",\"pkgsize\":14767608,\"quality_productid\":\"2316029690\",\"qzoneliked\":1,\"subordinate_product_id\":\"70267529;000116083638313731383039\"}\n" +
                ",\n" +
                "\"advertiser_id\":7421710,\n" +
                "\"pkg_download_schema\":\"taskApkId=0&packName=com.baidu.haokan&taskAppId=&appId=&via=ANDROIDQQ.FEED.ADVERTISE.mv4qmyvwilt5m05&versionCode=205&channel=000116083638313731383039;70267529\",\n" +
                "\"cfg\":{\n" +
                "\"pt\":11}\n" +
                "}\n" +
                "],\n" +
                "\"cfg\":{\n" +
                "\"playmod\":1,\n" +
                "\"playcfg\":{\n" +
                "\"pw\":1280,\n" +
                "\"adtype\":1,\n" +
                "\"ph\":7220,\n" +
                "\"h\":720,\n" +
                "\"w\":1280,\n" +
                "\"pt\":11,\n" +
                "\"th\":300,\n" +
                "\"tw\":300,\n" +
                "\"dir\":1,\n" +
                "\"hnum\":0,\n" +
                "\"wnum\":1,\n" +
                "\"noping\":0,\n" +
                "\"adplaymode\":1,\n" +
                "\"instancerpt\":0,\n" +
                "\"timingrpt\":0,\n" +
                "\"2009\":91,\n" +
                "\"11611\":2,\n" +
                "\"30153\":1,\n" +
                "\"80436\":1,\n" +
                "\"80658\":57,\n" +
                "\"80862\":3,\n" +
                "\"82012\":1,\n" +
                "\"82119\":1056002,\n" +
                "\"91041\":1,\n" +
                "\"91042\":1,\n" +
                "\"91246\":4000,\n" +
                "\"91377\":1,\n" +
                "\"91452\":2000,\n" +
                "\"91468\":1000,\n" +
                "\"91469\":50,\n" +
                "\"91555\":1000,\n" +
                "\"91557\":20,\n" +
                "\"91576\":1,\n" +
                "\"91605\":1,\n" +
                "\"91645\":1,\n" +
                "\"91732\":64765,\n" +
                "\"91745\":1,\n" +
                "\"91766\":1,\n" +
                "\"91882\":1,\n" +
                "\"91883\":1,\n" +
                "\"91884\":200,\n" +
                "\"91885\":200,\n" +
                "\"91470\":-8.000000,\n" +
                "\"91559\":0.001000,\n" +
                "\"91471\":\"dpa_daba#16\",\n" +
                "\"91553\":\"dpa_dact#2\",\n" +
                "\"91606\":\"lite_cvr_app\",\n" +
                "\"91607\":\"user_signature\",\n" +
                "\"cimg2hidetime\":10,\n" +
                "\"displayinterval\":60,\n" +
                "\"pollcommtime\":60,\n" +
                "\"displaymode\":1,\n" +
                "\"poll\":\"click\"}\n" +
                "}\n" +
                "}\n" +
                "}\n" +
                "}";
        Gson mGson = new Gson();
        VideoAdvertisement mVideoAdvertisement = mGson.fromJson(string, VideoAdvertisement.class);
        List<VideoAdvertisement.DataBean._$6030720636414512Bean.ListBean> mList = mVideoAdvertisement.getData().get_$6030720636414512().getList();
        List<String> title = new ArrayList<>( );
        List<String> imgUrl =  new ArrayList<>();
        for (int i=0;i<mList.size();i++){
            title.add(mList.get(i).getTxt());
            imgUrl.add(mList.get(i).getImg());
        }
        Map<String,List<String>> map = new HashMap<>();
        map.put("title",title);
        map.put("imgUrl",imgUrl);
        if (view!=null){
            view.showBanner(map);
            LogUtil.d("view.showBanner(map)");
        }

        /*String baseUrl="http://mi.gdt.qq.com/" ;
              String suffixUrl =
"gdt_mview.fcg?fc=1&datatype=2&encext=ED82Q2_U5g5Eb19PcODMvBdHLBqqyozIYfOGpOAYD8S_nuAV84OcvSNtLcTSTp75yPHeRDKa0JcPfezMSUJ6TQ&count=5&encver=1&adposcount=1&r=0.7511382645424696&ext=%7B%22req%22%3A%7B%22m1%22%3A%22608551f1fbd239c1bbb9f50575b7ccdb%22%2C%22m2%22%3A%222c7e0af3d64b39c29c78055e703860d7%22%2C%22m3%22%3A%22907324076cf02f650713ff095d94ee36%22%2C%22m6%22%3A%225f62483a188d1c0fef6b7be151751e10%22%2C%22muidtype%22%3A1%2C%22muid%22%3A%22608551f1fbd239c1bbb9f50575b7ccdb%22%2C%22placement_type%22%3A9%2C%22render_type%22%3A1%2C%22conn%22%3A1%2C";
              ViseHttp.GET().baseUrl(baseUrl).suffixUrl(suffixUrl)
                .tag(this).request(new CallBack<VideoAdvertisement>() {
            @Override
            public void onFail(int errCode, String errMsg) {
                super.onFail(errCode, errMsg);
            }

            @Override
            public void onSuccess(VideoAdvertisement videoAdvertisement) {
                LogUtil.d("onSuccess gerAD");
                if (videoAdvertisement!=null&&videoAdvertisement.getData()!=null) {
                    List<VideoAdvertisement.DataBean._$6030720636414512Bean.ListBean> mList = videoAdvertisement.getData().get_$6030720636414512().getList();
                    List<String> title = new ArrayList<>(mList.size());
                    List<String> imgUrl =  new ArrayList<>(mList.size());
                    for (int i=0;i<mList.size();i++){
                        title.add(mList.get(i).getTxt());
                        imgUrl.add(mList.get(i).getImg());
                    }
                    Map<String,List<String>> map = new HashMap<>();
                    map.put("title",title);
                    map.put("imgUrl",imgUrl);
                    if (view!=null){
                        view.showBanner(map);
                        LogUtil.d("view.showBanner(map)");
                    }
                }

            }
        });*/
    }

    @Override
    public void detachView() {
        ViseHttp. cancelTag(this);
        super.detachView();
    }
}

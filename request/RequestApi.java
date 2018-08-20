package com.example.lenovo.myapplication.request;

import com.example.lenovo.myapplication.bean.ArticlePicDetail;
import com.example.lenovo.myapplication.bean.BaseUrl;
import com.example.lenovo.myapplication.bean.DetailImgUrl;
import com.example.lenovo.myapplication.bean.FeedBack;
import com.example.lenovo.myapplication.bean.HotPhoto;
import com.example.lenovo.myapplication.bean.NewPhoto;
import com.example.lenovo.myapplication.bean.RecommendLabel;
import com.example.lenovo.myapplication.bean.SearchType;
import com.example.lenovo.myapplication.bean.VersionCode;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lenovo on 2018/8/11.
 * auther:lenovo
 * Date：2018/8/11
 */

public interface RequestApi {

    String appkey = "appkey:06E0C9C16A169AEC";
    String api_version = "api_version:v20";
    String applicationId = "applicationId:1";
    String channel = "channel:GW_3";
    String client_version = "client_version:1.6.3";
    String refer = "refer:http://mmapi.yomei.tv/";
    String did = "did:865166020148113,460005611084481,fee69b33c464d6e2";
    String sign = "sign:20B140BADA51CE0DECE7ACFC56A672C0";
    String versionSign = "sign:C7385B6895EAE9602050567ABE8BBFE0";
    String initDataSign = "sign:A804EC79FEB091845E05FBE4BFAFEF63";

    String token = "token:eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjE5MDg5Mjk2OTYxLCJyYW5kb20iOjE1MzQwMDg5OTQ3MDksImxvZ2luX3R5cGUiOjF9.zn3hDDh-UCumh8Pvk7Klg1VL-dw8dneUtYY3aGGhKUY";
    String nullToken = "token: ";

    @Headers({appkey, api_version, applicationId, channel, client_version, refer, did, token})
    @GET("getHotPicList")
    Observable<HotPhoto> getHotImg(@Header("sign") String sign, @Query("lastIndex") int index);

    @Headers({appkey, api_version, applicationId, channel, client_version, refer, did, token})
    @GET("getNewPicList")
    Observable<NewPhoto> getNewImg(@Header("sign") String sign, @Query("lastIndex") int index);

    @Headers({appkey, api_version, applicationId, channel, client_version, refer, did, initDataSign, token})
    @GET("initData")
    Observable<BaseUrl> initData();

    @Headers({appkey, api_version, applicationId, channel, client_version, refer, did, versionSign, nullToken})
    @GET("checkVersion")
    Observable<VersionCode> checkVersion(@Query("versionCode") String versionCode);

    String feedbackStatus = "sign:A804EC79FEB091845E05FBE4BFAFEF63";

    @Headers({appkey, api_version, applicationId, channel, client_version, refer, did, feedbackStatus, token})
    @GET("feedbackStatus")
    Observable<FeedBack> feedBack();

    String recommend = "sign:A804EC79FEB091845E05FBE4BFAFEF63";

    @Headers({appkey, api_version, applicationId, channel, client_version, refer, did, recommend, token})
    @GET("getRecommendLabelList21")
    Observable<RecommendLabel> getRecommendLabelList21();

    String SearchSign1 = "sign:68DD431B93DE5631261FAA67B196E341";

    @Headers({appkey, api_version, applicationId, channel, client_version, refer, did, SearchSign1, token})
    @GET("getSearchKeywordsList21")
    Observable<SearchType> getSearchKeywordsList1(@Query("searchType") String searchType);

    String SearchSign2 = "sign:0E43B21D4E160A3ECCA9CDBD955EC428";

    @Headers({appkey, api_version, applicationId, channel, client_version, refer, did, SearchSign2, token})
    @GET("getSearchKeywordsList21")
    Observable<SearchType> getSearchKeywordsList2(@Query("searchType") String searchType);

    @Headers({appkey, api_version, applicationId, channel, client_version, refer, did, token})
    @GET("getArticlePicDetail")
    Call<DetailImgUrl> getDetailImgUrl(@Header("sign") String sign, @Query("apid") int apid);

    @Headers({appkey, api_version, applicationId, channel, client_version, refer, did, token})
    @GET("getArticleComment")
    Call<ArticlePicDetail> getArticleComment(@Header("sign") String sign, @Query("aid") int aid, @Query("articleType") String articleType, @Query("page") String page);
}

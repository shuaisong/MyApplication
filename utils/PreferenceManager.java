package com.example.lenovo.myapplication.utils; /**
 * Copyright (C) 2016 Hyphenate Inc. All rights reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.lenovo.myapplication.bean.BaseUrl;
import com.example.lenovo.myapplication.bean.RecommendLabel;
import com.example.lenovo.myapplication.bean.SearchType;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class PreferenceManager {
    /**
     * name of preference
     */
    public static final String PREFERENCE_NAME = "saveInfo";
    private static PreferenceManager mPreferencemManager;
    private static SharedPreferences.Editor editor;
    private SharedPreferences mMSharedPreferences;

    @SuppressLint("CommitPrefEdits")
    private PreferenceManager(Context cxt) {
        mMSharedPreferences = cxt.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        editor = mMSharedPreferences.edit();
    }

    public static synchronized void init(Context cxt) {
        if (mPreferencemManager == null) {
            mPreferencemManager = new PreferenceManager(cxt);
        }
    }

    /**
     * get instance of PreferenceManager
     *
     * @param
     * @return
     */
    public synchronized static PreferenceManager getInstance() {
        if (mPreferencemManager == null) {
            throw new RuntimeException("please init first!");
        }

        return mPreferencemManager;
    }

    public void setRow(int row) {
        editor.putInt("row", row);
        editor.apply();
    }

    public int getRow() {
        return mMSharedPreferences.getInt("row", 1);
    }

    public void setIsFirst(boolean isFirst) {
        editor.putBoolean("isFirst", isFirst);
        editor.apply();
    }

    public boolean getIsFirst() {
        return mMSharedPreferences.getBoolean("isFirst", true);
    }

    public void setUrl(BaseUrl.DataObjBean.UrlInfoBean urlInfo) {
        editor.putString("backUrlprefix", urlInfo.getBackUrlprefix());
        editor.putString("labelUrlprefix", urlInfo.getLabelUrlprefix());
        editor.putString("netCheckUrl", urlInfo.getNetCheckUrl());
        editor.putString("picSearchUrlprefix", urlInfo.getPicSearchUrlprefix());
        editor.putString("picSmallUrlprefix", urlInfo.getPicSmallUrlprefix());
        editor.putString("picThumbUrlprefix", urlInfo.getPicThumbUrlprefix());
        editor.putString("picUrlPrefix", urlInfo.getPicUrlPrefix());
        editor.putString("videoSearchUrlprefix", urlInfo.getVideoSearchUrlprefix());
        editor.putString("videoUrlPrefix", urlInfo.getVideoUrlPrefix());
        editor.putString("videoThumbUrlprefix", urlInfo.getVideoThumbUrlprefix());
        editor.apply();
    }

    public String getBackUrlprefix() {
        return mMSharedPreferences.getString("backUrlprefix", "http://pr.mm798.net/back");
    }

    public String getLabelUrlprefix() {
        return mMSharedPreferences.getString("labelUrlprefix", "http://pr.mm798.net/back");
    }

    public String getNetCheckUrl() {
        return mMSharedPreferences.getString("netCheckUrl", "http://only-380473-112-17-238-21.nstool.netease.com/");
    }

    public String getPicUrlPrefix() {
        return mMSharedPreferences.getString("picUrlPrefix", "http://img1.mm115.net");
    }

    public String getPicSearchUrlprefix() {
        return mMSharedPreferences.getString("picSearchUrlprefix", "http://prt.mm798.net:8080/search");
    }

    public String getPicSmallUrlprefix() {
        return mMSharedPreferences.getString("picSmallUrlprefix", "http://prt.mm798.net:8080/cover");
    }

    public String getVideoSearchUrlprefix() {
        return mMSharedPreferences.getString("videoSearchUrlprefix", "http://prt.mm798.net/search/video/");
    }

    public String getVideoThumbUrlprefix() {
        return mMSharedPreferences.getString("videoThumbUrlprefix", "http://prt.mm798.net/collection/video/");
    }

    public String getVideoUrlPrefix() {
        return mMSharedPreferences.getString("videoUrlPrefix", "http://prt.mm798.net/videocover");
    }

    public String getPicThumbUrlprefix() {
        return mMSharedPreferences.getString("picThumbUrlprefix", "http://prt.mm798.net:8080/collection");
    }

    public void saveSearchKey(SearchType.DataObjBean bean, String searchType) {
        List<SearchType.DataObjBean.KeywordsListBean> mKeywordsList = bean.getKeywordsList();
        Gson mGson = new Gson();
        String mToJson = mGson.toJson(mKeywordsList);
        editor.putString("SearchType" + searchType, mToJson).apply();
    }

    public <T> List<T> getSearchKey(String type) {
        List<T> beans = new ArrayList<>();
        String mVideoLabelList = mMSharedPreferences.getString("SearchType" + type, null);
        if (mVideoLabelList == null) {
            return beans;
        }
        Gson mGson = new Gson();
        beans = mGson.fromJson(mVideoLabelList, new TypeToken<List<T>>() {
        }.getType());
        return beans;
    }

    public <T> List<T> getRecommendLabelList() {
        List<T> beans = new ArrayList<>();
        String mVideoLabelList = mMSharedPreferences.getString("VideoLabelList", null);
        if (mVideoLabelList == null) {
            return beans;
        }
        Gson mGson = new Gson();
        beans = mGson.fromJson(mVideoLabelList, new TypeToken<List<T>>() {
        }.getType());
        return beans;
    }

    public void saveRecommend(RecommendLabel.DataObjBean bean) {
        List<RecommendLabel.DataObjBean.ArticleLabelListBean> mArticleLabelList = bean.getArticleLabelList();
        List<RecommendLabel.DataObjBean.VideoLabelListBean> mVideoLabelList = bean.getVideoLabelList();
        saveArticleLabelList(mArticleLabelList);
        saveVideoLabelList(mVideoLabelList);
    }

    private void saveVideoLabelList(List<RecommendLabel.DataObjBean.VideoLabelListBean> videoLabelList) {
        Gson mGson = new Gson();
        String mToJson = mGson.toJson(videoLabelList);
        editor.putString("VideoLabelList", mToJson).apply();
    }

    private void saveArticleLabelList(List<RecommendLabel.DataObjBean.ArticleLabelListBean> articleLabelList) {
        Gson mGson = new Gson();
        String mToJson = mGson.toJson(articleLabelList);
        editor.putString("ArticleLabelList", mToJson).apply();
    }
}

package com.example.lenovo.myapplication.bean.msg;

import com.example.lenovo.myapplication.bean.VersionCode;

/**
 * Created by lenovo on 2018/8/12.
 * auther:lenovo
 * Date：2018/8/12
 */

public class FindVersion  {
    public FindVersion(VersionCode.DataObjBean bean) {
        this.bean = bean;
    }

    private VersionCode.DataObjBean bean;

    public VersionCode.DataObjBean getBean() {
        return bean;
    }
}

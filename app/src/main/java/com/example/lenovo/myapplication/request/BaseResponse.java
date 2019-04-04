package com.example.lenovo.myapplication.request;

/**
 * Created by lenovo on 2018/8/14.
 * auther:lenovo
 * Date：2018/8/14
 */

public class BaseResponse<T> {

    private int code;//状态吗
    private String msg;//返回的提示消息
    private T data;//主要内容,因为不知道返回的会是什么类型,所以用泛型来表示


    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }


    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}

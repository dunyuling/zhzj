package com.aifeng.response;

import java.util.List;

/**
 * Created by pro on 17-3-24.
 */
public abstract class BaseResponse<T> {

    public int code;
    public String msg;
    public List<T> data;

    public void config(int code, String msg, List<T> data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}

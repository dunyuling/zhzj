package com.aifeng.response;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by pro on 17-3-30.
 */
@Data
@NoArgsConstructor
public class Response<T> {

    public int code;
    public String msg;
    public T data;

    public void config(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}

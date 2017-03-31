package com.aifeng.constant;

/**
 * Created by pro on 17-3-10.
 */
public enum RedirectionType {

    EXTERNAL("外部跳转"),INNER("内部跳转"),NONE("不跳转");

    private String rt;

    RedirectionType(String rt) {
        this.rt = rt;
    }

    public String getRt() {
        return rt;
    }
}

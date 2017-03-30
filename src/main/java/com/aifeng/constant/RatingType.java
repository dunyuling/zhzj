package com.aifeng.constant;

/**
 * Created by pro on 17-3-10.
 */
public enum RatingType {

    CONFERENCEHALL("会场"),BELIEVER("人员");

    private String rt;

    RatingType(String rt) {
        this.rt = rt;
    }

    public String getRt() {
        return rt;
    }
}

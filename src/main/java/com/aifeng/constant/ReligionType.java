package com.aifeng.constant;

/**
 * Created by pro on 17-3-10.
 */
public enum ReligionType {
    BUDDHISM("佛教"),TAOISM("道教"),CHRISTIANITY("基督教"),CATHOLICISM("天主教"),ISLAMISM("伊斯兰教"),OTHER("其它");

    private String rt;

    ReligionType(String rt) {
        this.rt = rt;
    }

    public String getRt() {
        return rt;
    }
}

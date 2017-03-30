package com.aifeng.constant;

/**
 * Created by pro on 17-3-10.
 */
public enum InnerRedirectionType {
    //    资讯, 商品, 门票, 评选,其它
    INFORMATION("资讯"), GOODS("商品"), TICKET("门票"), RATING("评选"), OTHER("其它");

    private String irt;

    InnerRedirectionType(String irt) {
        this.irt = irt;
    }

    public String getIrt() {
        return irt;
    }
}

package com.aifeng.constant;

/**
 * Created by pro on 17-3-10.
 */

public enum VerifyStatus {

//     待审核,审核通过,审核未通过
    PENDINGAUDIT("待审核"),AUDITTHROUGH("审核通过"),AUDITFAILURE("审核未通过");

    private String vs;

    VerifyStatus(String vs) {
        this.vs = vs;
    }

    public String getVs() {
        return vs;
    }


//    IDENTIFITY_0(0,"待审核"), IDENTIFITY_1(1,"审核通过"), IDENTIFITY_2(2,"审核未通过");
//
//    private int code;
//    private String value;
//
//
//    VerifyStatus(int code, String value){
//        this.code = code;
//        this.value = value;
//    }
//
//
//    public int getCode() {
//        return code;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }
//
//    public String getValue() {
//        return value;
//    }
//
//    public void setValue(String value) {
//        this.value = value;
//    }
}
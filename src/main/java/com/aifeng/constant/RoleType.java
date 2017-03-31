package com.aifeng.constant;

/**
 * Created by pro on 17-3-9.
 */
public enum RoleType {
    SUPERMANAGER("超级管理员"), RELIGIONMANAGER("宗教管理员"), PARTYSECRETARY("乡党委书记"), HIERARCH("教主"), BELIEVER("信徒");

    private String rt;

    RoleType(String rt) {
        this.rt = rt;
    }

    public String getRt() {
        return rt;
    }
}

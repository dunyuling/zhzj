package com.aifeng.constant;

import javax.persistence.ManyToOne;

/**
 * Created by pro on 17-3-9.
 */
public enum ManagerType {
//    超级管理员,宗教管理员,乡党委书记
    SUPERMANAGER("超级管理员"),RELIGIONMANAGER("宗教管理员"),PARTYSECRETARY("乡党委书记");

    private String mt;

    ManagerType(String mt) {
        this.mt = mt;
    }

    public String getMt() {
        return mt;
    }
}

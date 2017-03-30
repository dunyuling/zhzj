package com.aifeng.constant;

import com.aifeng.model.Information;

/**
 * Created by pro on 17-3-10.
 */
public enum InformationPublisher {
    /*政府("gov"), 宗教("religion");

    private String ip;

    InformationPublisher(String ip) {
        this.ip = ip;
    }

    public static InformationPublisher getIP(String ip) {
        if (ip.equals("gov")) {
            return 政府;
        } else {
            return 宗教;
        }
    }*/

    GOV("政府"),RELIGION("宗教");

    private String ip;

    InformationPublisher(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }
}
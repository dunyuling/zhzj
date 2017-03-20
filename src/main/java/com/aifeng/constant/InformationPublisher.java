package com.aifeng.constant;

/**
 * Created by pro on 17-3-10.
 */
public enum InformationPublisher {
    政府("gov"), 宗教("religion");

    private String ip;

    InformationPublisher(String ip) {
        this.ip = ip;
    }

    public static InformationPublisher getIP(String ip) {
        if (ip.equals("gov")) {
            return 政府;
        } else if (ip.equals("religion")) {
            return 宗教;
        }

        //TODO 这里的方式需要优化
        try {
            throw new Exception("传入枚举类型出现错误");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
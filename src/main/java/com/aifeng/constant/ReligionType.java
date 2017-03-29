package com.aifeng.constant;

/**
 * Created by pro on 17-3-10.
 */
public enum ReligionType {
    佛教("fo"), 道教("dao"), 基督教("jidu"), 天主教("tianzhu"), 伊斯兰教("yisilan"), 其它("qita");

    private String type;

    ReligionType(String type) {
        this.type = type;
    }

    public static ReligionType getType(String type) {
        if (type.equals("fo")) {
            return 佛教;
        } else if (type.equals("dao")) {
            return 道教;
        } else if (type.equals("jidu")) {
            return 基督教;
        } else if (type.equals("tianzhu")) {
            return 天主教;
        } else if (type.equals("yisilan")) {
            return 伊斯兰教;
        } else {
            return 其它;
        }
    }

    public static String getConParam(ReligionType religionType) {
        String param = "qita";
        switch (religionType) {
            case 佛教:
                param = "fo";
                break;
            case 道教:
                param = "dao";
                break;
            case 基督教:
                param = "jidu";
                break;
            case 天主教:
                param = "tianzhu";
                break;
            case 伊斯兰教:
                param = "yisilan";
                break;
            case 其它:
                break;
        }
        return param;
    }
}

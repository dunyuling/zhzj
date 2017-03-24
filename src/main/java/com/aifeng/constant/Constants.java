package com.aifeng.constant;

public interface Constants {

    /**
     * 未登录错误
     ***/
    int RET_NOLOGIN = -1;

    String SESSION_USER = "sessionUser";

    String SESSION_PERMISSIONS = "permissions";
//    String SESSION_ROLE_NAME = "roleName";

    /**
     * 图片起始路径
     *******/
//    String IMG_CONTEXTPATH = ImgPath.productIntroPath;

    String HTTP_IMG_PREFIX = "http://localhost:8080";

    String RET_SUC = "suc";

    //消息类型
    String REGISTER = "register";

    //头像名称前缀
    String AVATAR_PREFIX = "avatar_";

    //结算类型
    String SETTLE_CART = "1";
    String SETTLE_GOODS = "2";
    String SETTLE_WDZ = "3";
    String SETTLE_DZ = "4";

    String APK_PATH = "/app/";


    //订单状态
    String ORDER_STATE_NEW = "10";
    String ORDER_STATE_PAY = "20";

    //app名称
    String APP_NAME = "心意礼物";


    //第三方登陆  1:QQ  2:sina  3:weixin
    String AUTH_WX = "1";
    String AUTH_QQ = "2";
    String AUTH_SINA = "3";

    int MobileIndex = 4;
    int NotMobileIndex = 10;
}

package com.aifeng;

import java.io.File;

/**
 * Created by pro on 17-3-14.
 */
public class Util {

    public static void mkDir(String path) {
        File file = new File(path);
        if (!file.exists())
            file.mkdirs();
    }

    String key = "zhzj";

    public String getSign(String timestamp, String platform, String v, String data) {
        StringBuilder builder = new StringBuilder();
        builder.append(key);
        builder.append("timestamp").append(timestamp);
        builder.append("plat").append(platform);
        builder.append("v").append(v);
        builder.append("data").append(data);
        //	builder.append("tk").append(tk);
        builder.append(key);
        System.out.println("builder--" + builder);
        //	return Md5.getMd5(builder.toString());
        return AES.getMd5(builder.toString());
    }

    /*public void doReqWithParams() {
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        pairs.add(new NameValuePair("plat", this.plat));
        pairs.add(new NameValuePair("v", this.v));
        pairs.add(new NameValuePair("data", this.data));
        pairs.add(new NameValuePair("timestamp", this.timestamp));
        this.tk = this.tk == null ? "" : this.tk;
        pairs.add(new NameValuePair("tk", tk));
        String sign = getSign(this.timestamp, this.plat, this.v, this.data);
        pairs.add(new NameValuePair("sign", sign));
        System.out.println("5555--" + this.url);
        this.result = doClientPost(this.url, pairs.toArray(new NameValuePair[0]));
//	System.out.println(result);
    }*/
}

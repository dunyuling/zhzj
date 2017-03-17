package com.aifeng;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AuthCheckInterceptor extends HandlerInterceptorAdapter {

    Logger log = Logger.getLogger(AuthCheckInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String errorMsg = null;
        String timestamp = request.getParameter("timestamp");
        String plat = request.getParameter("plat");
        String v = request.getParameter("v");
        String data = request.getParameter("data");
        String sign = request.getParameter("sign");


        if (StringUtils.isNumeric(timestamp)) {
            int out = 5;  //请求过期时间5分钟
            if (System.currentTimeMillis() < (Long.valueOf(timestamp) + (out * 60 * 1000))) {
                if (!validateSign(timestamp, plat, v, data, sign)) {
                    log.info("签名缺失或请求参数与签名不符");
                    errorMsg = "非法请求";
                } else {
                    return true;
                }
            } else {
                errorMsg = "请求已过期";
                log.info("请求已过期");
            }
        } else {
            errorMsg = "请求已过期";
            log.info("参数timestamp不是有效的数字");
        }

        JSONObject json = new JSONObject();
        json.put("msg", errorMsg);
        json.put("ret", 0);
        writeToJson(response, json);
        return false;
        //return super.preHandle(request, response, handler);
    }

    /**
     * @param timestamp
     * @param plat
     * @param v
     * @param data
     * @param sign
     * @return
     */
    private boolean validateSign(String timestamp, String plat, String v, String data, String sign) {

        String key = PropUtil.getString("ws.request.sign.key");
        StringBuilder builder = new StringBuilder();
        builder.append(key);
        builder.append("timestamp").append(timestamp);
        builder.append("plat").append(plat);
        builder.append("v").append(v);
        builder.append("data").append(data);
        builder.append(key);
        log.info("==========" + builder.toString());
        System.out.println("builder: " + builder.toString());
        return sign != null && sign.equals(AES.getMd5(builder.toString()));
    }


    private void writeToJson(HttpServletResponse response, JSONObject json) throws IOException {
        response.setContentType("application/x-json");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.print(json.toJSONString());
        out.close();
    }
}

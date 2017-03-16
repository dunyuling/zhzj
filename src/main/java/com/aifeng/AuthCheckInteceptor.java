package com.aifeng;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AuthCheckInteceptor extends HandlerInterceptorAdapter {
	
	Logger log = Logger.getLogger(AuthCheckInteceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HandlerMethod methodHandler=(HandlerMethod) handler;
		String data = request.getParameter("data");
		String errorMsg = null;
		String method = ((HttpServletRequest)request).getMethod();
		String url = ((HttpServletRequest)request).getRequestURL().toString();

		String timestamp = request.getParameter("timestamp");
		String plat = request.getParameter("plat");
		String v = request.getParameter("v");
//		String data = request.getParameter("data");
		String sign = request.getParameter("sign");

		if(StringUtils.isNumeric(timestamp)){
			int out = 5;  //请求过期时间5分钟
			if(System.currentTimeMillis()<(Long.valueOf(timestamp) + (out * 60 * 1000))){
				if(!validateSign(timestamp, plat, v, data, sign)){
					log.info("签名缺失或请求参数与签名不符");
					errorMsg = "非法请求";
				}else{
					return true;
				}
			}else{
				errorMsg  = "请求已过期";
				log.info("请求已过期");
			}
		}else{
			errorMsg = "请求已过期";
			log.info("参数timestamp不是有效的数字");
		}
		if (errorMsg != null) {
			JSONObject json = new JSONObject();
			json.put("msg", errorMsg);
			json.put("ret", 0);
			writeToJson((HttpServletResponse) response, json);
			return false;
		}
		//return super.preHandle(request, response, handler);
		return true;
	}
	
	/**
	 * 
	 * @param timestamp
	 * @param plat
	 * @param v
	 * @param data
	 * @param sign
	 * @return
	 */
	private boolean validateSign(String timestamp, String plat, String v,String data, String sign){
		
 		String key = PropUtil.getString("ws.request.sign.key");
		StringBuilder builder = new StringBuilder();
		builder.append(key);
		builder.append("timestamp").append(timestamp);
		builder.append("plat").append(plat);
		builder.append("v").append(v);
		builder.append("data").append(data);
		/*if(StringUtils.isNotBlank(tk))
			builder.append("tk").append(tk);*/
		builder.append(key);
		log.info("=========="+builder.toString());
//		log.info(AES.getMd5(builder.toString())+"=="+sign);
//		String s = "zyaf_coretimestamp1484885128048platandroidv1.0data{\"areaInfo\":\"河北 石家庄市 井陉县\",\"mobPhone\":\"15136284727\",\"telPhone\":\"15136284727\",\"address\":\"xccfff\",\"cityId\":\"73\",\"isDefault\":\"0\",\"name\":\"ddd\",\"areaId\":\"3\"}zyaf_core";
//		String s = "zyaf_coretimestamp1484888937690platandroidv1.0data{\"areaInfo\":\"河北 石家庄市 井陉县\"}zyaf_core";
//		System.out.println(AES.getMd5(s));
	System.out.println(AES.getMd5(builder.toString()));
		if(sign != null && sign.equals(AES.getMd5(builder.toString()))){
			return true;
		}
		return false;
	}
	

	private void writeToJson(HttpServletResponse response, JSONObject json) throws IOException{
		response.setContentType("application/x-json");  
        PrintWriter out= response.getWriter();
        out.print(json.toJSONString());  
        out.close();
	}
	
	
	public static void main(String[] args) {
//		String sa = "zyaf_coretimestamp1484876620059platandroidv1.0data{\"areaInfo\":\"河北 石家庄市 井陉县\",\"mobPhone\":\"15136284727\",\"telPhone\":\"15136284727\",\"address\":\"cffggg\",\"cityId\":\"73\",\"isDefault\":\"0\",\"name\":\"dff\",\"areaId\":\"3\"}zyaf_core";
		String sa = "zyaf_coretimestamp1484890179071platandroidv1.0data{\"areaInfo\":\"河北 石家庄市 井陉县\"}zyaf_core";
//		StringBuilder sb = new StringBuilder();
//		sb.append(sa);
		System.out.println(AES.getMd5(sa));
		
		//System.out.println(AES.getMd5("zyaf_coretimestamp1484876004565platandroidv1.0data{}tk42970b233b3eb6698bb5fd3669b922c2dbd6289ac956da029fe8c180f18940b3zyaf_core"));
	}
}

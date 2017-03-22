package com.aifeng;

import com.aifeng.constant.ImgPath;
import org.apache.http.NameValuePair;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pro on 17-3-14.
 */
public class Util {

    public static void mkDir(String path) {
        File file = new File(path);
        if (!file.exists())
            file.mkdirs();
    }

    static String key = "zhzj";

    public static String getSign(String timestamp, String platform, String v, String data) {
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


    public static String uploadImg(HttpServletRequest request, String realPath) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        String imgRealPathDir = request.getSession().getServletContext().getRealPath(realPath);
        mkDir(imgRealPathDir);

        MultipartFile multipartFile = multipartRequest.getFile("img");
        String imgName = multipartFile.getOriginalFilename();
        String fullPath = imgRealPathDir + File.separator + imgName;

        System.out.println("logImageName：" + imgName);
        File file = new File(fullPath);
        try {
            multipartFile.transferTo(file);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        return realPath + File.separator + imgName;
    }

    public static String[] uploadImgs(HttpServletRequest request, String realPath) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        String imgRealPathDir = request.getSession().getServletContext().getRealPath(realPath);
        mkDir(imgRealPathDir);
        MultiValueMap<String, MultipartFile> multiValueMap = multipartRequest.getMultiFileMap();

        String[] paths = null;
        for (String key : multiValueMap.keySet()) {
            if (key.equals("product_slide")) {
                List<MultipartFile> multipartFiles = multiValueMap.get(key);
                int i = 0;
                paths = new String[multipartFiles.size()];
                for (MultipartFile multipartFile : multipartFiles) {
                    String imgName = multipartFile.getOriginalFilename();
                    String fullPath = imgRealPathDir + File.separator + imgName;

                    System.out.println("logImageName：" + imgName);
                    File file = new File(fullPath);
                    try {
                        multipartFile.transferTo(file);
                    } catch (IllegalStateException | IOException e) {
                        e.printStackTrace();
                    }
                    paths[i++] = realPath + File.separator + imgName;
                }
            }
        }
        return paths;
    }

    public static String[] editImgs(HttpServletRequest request,String realPath) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        String imgRealPathDir = request.getSession().getServletContext().getRealPath(realPath);
        mkDir(imgRealPathDir);
        MultiValueMap<String, MultipartFile> multiValueMap = multipartRequest.getMultiFileMap();

        String[] paths = null;
        for (String key : multiValueMap.keySet()) {
            if (key.equals("product_slide")) {
                List<MultipartFile> multipartFiles = multiValueMap.get(key);
                int i = 0;
                paths = new String[multipartFiles.size()];
                for (MultipartFile multipartFile : multipartFiles) {
                    if(!multipartFile.isEmpty()) {
                        String imgName = multipartFile.getOriginalFilename();
                        String fullPath = imgRealPathDir + File.separator + imgName;

                        System.out.println("logImageName：" + imgName);
                        File file = new File(fullPath);
                        try {
                            multipartFile.transferTo(file);
                        } catch (IllegalStateException | IOException e) {
                            e.printStackTrace();
                        }
                        paths[i++] = realPath + File.separator + imgName;
                    } else {
                        paths[i++] = null;
                    }
                }
            }
        }
        return paths;
    }


    public static String editImg(HttpServletRequest request, String realPath) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        String imgRealPathDir = request.getSession().getServletContext().getRealPath(realPath);
        Util.mkDir(imgRealPathDir);

        String imgRelativePath = null;
        MultipartFile multipartFile = multipartRequest.getFile("img");
        if (!multipartFile.isEmpty()) {
            String fullPath = imgRealPathDir + File.separator + multipartFile.getOriginalFilename();

            File file = new File(fullPath);
            try {
                multipartFile.transferTo(file);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
            imgRelativePath = realPath + File.separator + multipartFile.getOriginalFilename();
        }
        return imgRelativePath;
    }
}

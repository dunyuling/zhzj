package com.aifeng;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pro on 17-3-7.
 */
public class Main {
    public static void main(String[] args) {
        /*Student student = new Student("001","lhg","3129");
        System.out.println(student.getId());*/

        /*String s1 = "/home/pro/IdeaProjects/zhzj/build/libs/exploded/zhzj-1.0-SNAPSHOT.war/img/ad";
        String s2 = "/img/ad";
        String s3 = s1.substring(0,s1.indexOf(s2));
        System.out.println(s3);*/

//        new File("/home/pro/IdeaProjects/zhzj/build/libs/exploded/zhzj-1.0-SNAPSHOT.war/img/ad/00a20fb9d4c22083d91be9e2b8b5f0d8.jpg").deleteOnExit();
//        new File("/home/pro/IdeaProjects/zhzj/build/libs/exploded/zhzj-1.0-SNAPSHOT.war/img/ad/7f5b11afb18731f19c8c8b56fe4f8a9d.jpg").deleteOnExit();


//        System.out.println(AES.getMd5("zhzj"));
        /*try {
            System.out.println(AES.Encrypt("zhzj", AES.getMd5("zhzj")));
            System.out.println(AES.Decrypt("b333e864ba92921eb5f19e307bb4156e", AES.getMd5("zhzj")));
        } catch (Exception e) {
            e.printStackTrace();
        }*/

//        String result = Util.getSign(String.valueOf(System.currentTimeMillis()),"android","1.0","aaa");
//        System.out.println("result: " + result);
//        System.out.println(String.valueOf(System.currentTimeMillis()));


        /*HttpProcessor httpproc = HttpProcessorBuilder.create()
                .add(new RequestContent())
                .add(new RequestTargetHost())
                .add(new RequestConnControl())
                .add(new RequestUserAgent("Test/1.1"))
                .add(new RequestExpectContinue(true)).build();

        HttpRequestExecutor httpexecutor = new HttpRequestExecutor();

        HttpCoreContext coreContext = HttpCoreContext.create();
        HttpHost host = new HttpHost("www.baidu.com", 8080);
        coreContext.setTargetHost(host);

        DefaultBHttpClientConnection conn = new DefaultBHttpClientConnection(8 * 1024);
        ConnectionReuseStrategy connStrategy = DefaultConnectionReuseStrategy.INSTANCE;

        try {

            String[] targets = {
                    "/",
                    "/servlets-examples/servlet/RequestInfoExample",
                    "/somewhere%20in%20pampa"};

            for (int i = 0; i < targets.length; i++) {
                if (!conn.isOpen()) {
                    Socket socket = new Socket(host.getHostName(), host.getPort());
                    conn.bind(socket);
                }
                BasicHttpRequest request = new BasicHttpRequest("GET", targets[i]);
                System.out.println(">> Request URI: " + request.getRequestLine().getUri());

                httpexecutor.preProcess(request, httpproc, coreContext);
                HttpResponse response = httpexecutor.execute(request, conn, coreContext);
                httpexecutor.postProcess(response, httpproc, coreContext);

                System.out.println("<< Response: " + response.getStatusLine());
                System.out.println(EntityUtils.toString(response.getEntity()));
                System.out.println("==============");
                if (!connStrategy.keepAlive(response, coreContext)) {
                    conn.close();
                } else {
                    System.out.println("Connection kept alive...");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/


    }

    public static void testTemplate() {
        //TODO 参数传递不过去
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
        Map<String, Object> map = new HashMap<>();
        map.put("key", "zhzj");
        map.put("timestamp", System.currentTimeMillis());
        map.put("plat", "android");
        map.put("v", "1.0");
        map.put("data", "data");
        map.put("key", "zhzj");
        String sign = AES.getMd5(map.toString());
        map.put("sign", sign);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("v", "1.0");
        HttpEntity<?> entity = new HttpEntity<>(body);

        ResponseEntity responseEntity = restTemplate.exchange("http://localhost:8080/mobile/ad.json",HttpMethod.POST, entity,String.class,map);
        System.out.println(responseEntity);
    }

    private static ClientHttpRequestFactory getClientHttpRequestFactory() {
        int timeout = 5000;
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(timeout)
                .setConnectionRequestTimeout(timeout)
                .setSocketTimeout(timeout)
                .build();
        CloseableHttpClient client = HttpClientBuilder
                .create()
                .setDefaultRequestConfig(config)
                .build();
        return new HttpComponentsClientHttpRequestFactory(client);
    }

    public static void testSign() {
        /*Provider[] providers = Security.getProviders();
        for (Provider provider : providers) {
            System.out.println("Provider : " + provider.getName());
            Set<Provider.Service> services = provider.getServices();
            for (Provider.Service service : services) {
                if (service.getType().equals("Signature")) {
                    System.out.println("\t" + service.getAlgorithm());
                }
            }
        }*/

        //2
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        keyPairGenerator.initialize(2048); // KeySize
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        try {
            System.out.println("privateKey: " + new String(privateKey.getEncoded(), "ISO-8859-1") + "\n publicKey: " + publicKey);
        } catch (UnsupportedEncodingException e) {

        }

        //3
        byte[] data = "sign me".getBytes();
        Signature signature = null;
        try {
            signature = Signature.getInstance("SHA256withRSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            signature.initSign(privateKey);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        try {
            signature.update(data);
        } catch (SignatureException e) {
            e.printStackTrace();
        }
        byte[] signedData = new byte[0];
        try {
            signedData = signature.sign();
        } catch (SignatureException e) {
            e.printStackTrace();
        }


        //4
        try {
            signature.initVerify(publicKey);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        try {
            signature.update(data);
        } catch (SignatureException e) {
            e.printStackTrace();
        }
        try {
            if (signature.verify(signedData)) {
                System.out.println("Verified");
            } else {
                System.out.println("Something is wrong");
            }
        } catch (SignatureException e) {
            e.printStackTrace();
        }
    }
}

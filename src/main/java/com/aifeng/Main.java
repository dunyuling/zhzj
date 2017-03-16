package com.aifeng;

import com.aifeng.model.Student;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Set;

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

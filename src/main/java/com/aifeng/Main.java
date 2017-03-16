package com.aifeng;

import com.aifeng.model.Student;

import java.io.File;

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
        new File("/home/pro/IdeaProjects/zhzj/build/libs/exploded/zhzj-1.0-SNAPSHOT.war/img/ad/7f5b11afb18731f19c8c8b56fe4f8a9d.jpg").deleteOnExit();
    }
}

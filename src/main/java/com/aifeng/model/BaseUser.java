package com.aifeng.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table
@Entity
//这个表是空表，不存储任何内容
public class BaseUser {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String password;

    private String photo;

    private String mobileNum;

//    //这个字段应该不用填写
//    private String validateCode;

    private String IDNum;

    private String address;

    private Date createTime;

    private Date updateTime;
}
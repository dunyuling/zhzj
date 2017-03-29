package com.aifeng.model;

import com.aifeng.constant.ReligionType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Table
@Entity
public class Scripture { //经文

    @Id
    @GeneratedValue
    private long id;

    private String coverImg;

    private String title;

    private String content;

    private String descImg;

//    private String type; //TODO 忘记了具体含义

    @Enumerated(EnumType.STRING)
    private ReligionType religionType;

    private Date createTime;

    private Date updateTime;
}

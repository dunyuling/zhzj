package com.aifeng.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@NoArgsConstructor
@Table
@Entity
public class Location {


    @Id
    private long id;

    //TODO 具体信息待定
    //方案一：center + radius
    //方案二：使用联通基站

    private Date createTime;

    private Date updateTime;
}

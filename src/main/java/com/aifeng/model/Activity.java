package com.aifeng.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by pro on 17-3-10.
 */
@Data
@NoArgsConstructor
@Entity
@Table
public class Activity {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    private String content;

    private Date beginTime;

    private Date endTime;

    private Date createTime;

    private Date updateTime;

    @OneToOne
    private Manager manager;

    //TODO 区域如何表示
}

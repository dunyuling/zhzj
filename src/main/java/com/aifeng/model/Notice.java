package com.aifeng.model;

import com.aifeng.constant.ReligionType;
import com.aifeng.constant.VerifyStatus;
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
public class Notice {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    private String img;

    private String content;

    @Enumerated(EnumType.STRING)
    private ReligionType religionType;

    @Enumerated(EnumType.STRING)
    private VerifyStatus verifyStatus;

    private String denyReason;

    //TODO 推送地域如何表示

    private Date createTime;

    private Date updateTime;

    @OneToOne
    private Believer believer;
}

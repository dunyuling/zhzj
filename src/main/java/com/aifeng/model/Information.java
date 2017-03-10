package com.aifeng.model;

import com.aifeng.constant.InformationPublisher;
import com.aifeng.constant.ManagerType;
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
public class Information {

    @Id
    @GeneratedValue
    private long id;

    private String img;

    private String title;

    private String content;

    private String visitTimes;

    private Date publishTime;

    @Enumerated(EnumType.STRING)
    private InformationPublisher informationPublisher;

    @Enumerated(EnumType.STRING)
    private VerifyStatus verifyStatus;

    private String denyReason;

    private Date createTime;

    private Date updateTime;
}

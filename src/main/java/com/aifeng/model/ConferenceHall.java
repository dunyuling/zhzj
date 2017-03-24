package com.aifeng.model;

import com.aifeng.constant.ReligionType;
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
public class ConferenceHall { //会场门票

    @Id
    @GeneratedValue
    private long id;

    private String img;

    private String name;

    private String address;

    private String externalLink;

    @Enumerated(EnumType.STRING)
    private ReligionType religionType;

    private Date createTime;

    private Date updateTime;
}

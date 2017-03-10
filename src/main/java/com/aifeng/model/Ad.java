package com.aifeng.model;

import com.aifeng.constant.InnerRedirectionType;
import com.aifeng.constant.RedirectionType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by pro on 17-3-10.
 */
@Data
@NoArgsConstructor
@Table
@Entity
public class Ad {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    private Date createTime;

    private Date updateTime;

    @Enumerated(EnumType.STRING)
    private RedirectionType redirectionType;

    @Enumerated(EnumType.STRING)
    private InnerRedirectionType innerRedirectionType;
}
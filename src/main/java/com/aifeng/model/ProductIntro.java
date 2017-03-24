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
@Table
@Entity
public class ProductIntro {

    @Id
    @GeneratedValue
    private long id;

    private String htmlFrag;

    private Date createTime;

    private Date updateTime;

    @OneToOne
    private Product product;
}

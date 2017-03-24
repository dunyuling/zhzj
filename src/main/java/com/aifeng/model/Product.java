package com.aifeng.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by pro on 17-3-10.
 */
//@ToString
@Data
@NoArgsConstructor
@Table
@Entity
public class Product {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    private String img;

    private float price;

    private String seller;

    private String telephone;

    private Date createTime;

    private Date updateTime;

    @OneToMany(mappedBy = "product")
    private List<ProductSlide> productSlideList = new ArrayList<>();

    @OneToOne(mappedBy = "product")
    private ProductIntro productIntro;
}

package com.aifeng.model;

import com.aifeng.constant.ReligionType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
@ToString
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

    @Enumerated(EnumType.STRING)
    private ReligionType religionType;

    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    private List<ProductSlide> productSlideList = new ArrayList<>();

    @OneToOne(mappedBy = "product")
    @JsonManagedReference
    private ProductIntro productIntro;
}

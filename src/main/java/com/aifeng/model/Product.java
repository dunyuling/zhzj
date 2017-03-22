package com.aifeng.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.*;

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

    @OneToMany(mappedBy = "product")
    private List<ProductIntro> productIntroList = new ArrayList<>();


}

package com.aifeng.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class ProductSlide {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    private String img;

    private Date createTime;

    private Date updateTime;

    @ManyToOne
    private Product product;
}

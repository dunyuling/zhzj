package com.aifeng.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by pro on 17-3-10.
 */
@Data
@NoArgsConstructor
@Table
@Entity
public class RatingObj {

    //TODO 如何添加
    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;

    @OneToOne(mappedBy = "ratingObj")

    private RatingResult ratingResult;
}

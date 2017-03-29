package com.aifeng.model;

import com.aifeng.constant.RatingType;
import com.aifeng.constant.ReligionType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

/**
 * Created by pro on 17-3-10.
 */
@Data
@NoArgsConstructor
@Table
@Entity
public class Rating {

    @Id
    @GeneratedValue
    private long id;

    private String img;

    private String name;

    private String content;

    @Enumerated(EnumType.STRING)
    private ReligionType religionType;

    @Enumerated(EnumType.STRING)
    private RatingType rt;

    private Date createTime;

    private Date updateTime;

    @OneToMany(mappedBy = "rating")
    @JsonManagedReference
    private List<RatingObj> ratingObjList = new ArrayList<>();
}

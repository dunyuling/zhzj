package com.aifeng.model;

import com.aifeng.constant.RatingType;
import com.aifeng.constant.ReligionType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    private RatingType rt;

    private Date createTime;

    private Date updateTime;

    @OneToMany(mappedBy = "id")
    private Set<RatingObj> ratingObjSet = new HashSet<>();

    @OneToOne(mappedBy = "rating")
    private RatingResult ratingResult;
}

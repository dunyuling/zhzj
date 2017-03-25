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

    @ManyToOne
    private Rating rating;

    @OneToOne
    private Believer believer;

    @OneToOne
    private ConferenceHall conferenceHall;

    @OneToOne(mappedBy = "ratingObj")
    private RatingResult ratingResult;
}

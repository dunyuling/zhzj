package com.aifeng.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonBackReference
    private Rating rating;

    @OneToOne
    @JsonManagedReference
    private Believer believer;

    @OneToOne
    @JsonManagedReference
    private ConferenceHall conferenceHall;

    @OneToOne(mappedBy = "ratingObj")
    @JsonManagedReference
    private RatingResult ratingResult;
}

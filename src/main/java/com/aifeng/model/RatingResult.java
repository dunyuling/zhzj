package com.aifeng.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by pro on 17-3-10.
 */
@Data
@NoArgsConstructor
@Entity
@Table
public class RatingResult {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    @JsonBackReference
    private RatingObj ratingObj;

    private int ticketNum;
}

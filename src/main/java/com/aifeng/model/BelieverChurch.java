package com.aifeng.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by pro on 17-3-31.
 */
@Data
@NoArgsConstructor
@Table
@Entity
public class BelieverChurch {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private Believer believer;

    @ManyToOne
    @JsonBackReference
    private Church church;
}

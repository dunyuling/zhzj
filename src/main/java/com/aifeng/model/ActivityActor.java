package com.aifeng.model;

import javax.persistence.*;

/**
 * Created by pro on 17-3-31.
 */
@Entity
@Table
public class ActivityActor {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    private Activity activity;

    @OneToOne
    private Believer believer;
}

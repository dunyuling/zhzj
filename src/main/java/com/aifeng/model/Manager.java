package com.aifeng.model;

import javax.persistence.*;

/**
 * Created by pro on 17-3-9.
 */
@Table
@Entity
public class Manager extends Believer {

    @Column
    private String managerType;
}

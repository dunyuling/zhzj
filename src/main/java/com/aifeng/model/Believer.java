package com.aifeng.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by pro on 17-3-9.
 */

@Data
@NoArgsConstructor
@Table
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Believer {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

    @Column
    private String mobileNum;

    @Column
    private String validateCode;

    @Column
    private String church;

    @Column
    private String IDNum;

    @Column
    private String address;
}

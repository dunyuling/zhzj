package com.aifeng.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by pro on 17-3-10.
 */
@Data
@NoArgsConstructor
@Table
@Entity
public class BelieverPath {

    @Id
    @GeneratedValue
    private int id;

    private Date date;

    //TODO 位置如何表示
}

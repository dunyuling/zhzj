package com.aifeng.model;

import com.aifeng.constant.VerifyStatus;
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
public class Church {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String address;

    private String responsiblePerson;

    private String telephone;

    private int believerNum;

    @Enumerated(EnumType.STRING)
    private VerifyStatus verifyStatus;

    private String denyReason;
}
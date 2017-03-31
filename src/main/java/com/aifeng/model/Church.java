package com.aifeng.model;

import com.aifeng.constant.ReligionType;
import com.aifeng.constant.VerifyStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Church {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String address;

    private String responsiblePerson;

    private String telephone;

    private int believerNum;

    @Enumerated(value = EnumType.STRING)
    private ReligionType religionType;

    @Enumerated(EnumType.STRING)
    private VerifyStatus verifyStatus;

    private String denyReason;

    private Date createTime;

    private Date updateTime;

    @OneToMany(mappedBy = "church")
    private Set<BelieverChurch> believerChurchSet = new HashSet<>();
}
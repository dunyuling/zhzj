package com.aifeng.model;

import com.aifeng.constant.ReligionType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by pro on 17-3-9.
 */

@Data
@NoArgsConstructor
@Table
@Entity
//教主也在此列
public class Believer extends BaseUser {

    @Enumerated(EnumType.STRING)
    private ReligionType religionType;

    private String frequentedAddress;

    private boolean isLeader;//是不是教主

    @OneToOne(mappedBy = "believer")
    private NoticeReader noticeReader;

    @OneToMany(mappedBy = "id")
    private Set<BelieverPath> believerPathSet = new HashSet<>();

}

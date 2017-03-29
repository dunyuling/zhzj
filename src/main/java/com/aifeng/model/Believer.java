package com.aifeng.model;

import com.aifeng.constant.ReligionType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.net.IDN;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by pro on 17-3-9.
 */

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Table
@Entity
//教主也在此列
public class Believer extends BaseUser {

    public void config(String name, String password, String photo,
                       String mobileNum, String address, ReligionType religionType,
                       String frequentedAddress, boolean isLeader) {
        setName(name);
        //TODO 找到一种方式去加密password
        setPassword(password);
        setPhoto(photo);
        setMobileNum(mobileNum);
        setAddress(address);
        setReligionType(religionType);
        setFrequentedAddress(frequentedAddress);
        setLeader(isLeader);
    }


    @Enumerated(EnumType.STRING)
    private ReligionType religionType;

    private String frequentedAddress;

    private boolean isLeader;//是不是教主

    @OneToOne(mappedBy = "believer")
    private NoticeReader noticeReader;

    @OneToMany(mappedBy = "id")
    private Set<BelieverPath> believerPathSet = new HashSet<>();

    @OneToOne(mappedBy = "believer",optional = true)
    @JsonBackReference
    private RatingObj ratingObj;

}

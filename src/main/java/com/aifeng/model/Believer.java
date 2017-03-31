package com.aifeng.model;

import com.aifeng.constant.ReligionType;
import com.aifeng.constant.RoleType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import sun.security.rsa.RSASignature;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Table
@Entity
public class Believer {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String password;

    private String photo;

    private String mobileNum;

    private String IDNum;

    private String address;

    private Date createTime;

    private Date updateTime;

    @Enumerated(EnumType.STRING)
    private ReligionType religionType;

    @Enumerated(value = EnumType.STRING)
    private RoleType roleType;

    public void config(String name, String password, String photo,
                       String mobileNum, String address, ReligionType religionType) {
        setName(name);
        //TODO 找到一种方式去加密password
        setPassword(password);
        setPhoto(photo);
        setMobileNum(mobileNum);
        setAddress(address);
        setReligionType(religionType);
    }

    @OneToMany(mappedBy = "believer")
    private Set<BelieverChurch> believerChurchSet = new HashSet<>();

    @OneToMany(mappedBy = "believer")
    private Set<BelieverPath> believerPathSet = new HashSet<>();

    @OneToOne(mappedBy = "believer")
    @JsonBackReference
    private RatingObj ratingObj;

    @OneToOne(mappedBy = "believer")
    private Notice notice;

    @OneToOne(mappedBy = "believer")
    private NoticeReader noticeReader;

    @OneToOne(mappedBy = "believer")
    private Activity activity;

    @OneToOne(mappedBy = "believer")
    private ActivityActor activityActor;
}

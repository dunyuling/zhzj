package com.aifeng.model;

import com.aifeng.constant.ManagerType;
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
public class Manager extends BaseUser {

    @Enumerated(EnumType.STRING)
    private ManagerType managerType;

    @OneToOne(mappedBy = "manager")
    private Notice notice;

    @OneToOne(mappedBy = "manager")
    private NoticeReader noticeReader;

    @OneToOne(mappedBy = "manager")
    private Activity activity;
}

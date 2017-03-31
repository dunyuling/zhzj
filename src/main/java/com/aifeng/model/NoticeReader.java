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
public class NoticeReader {

    @Id
    @GeneratedValue
    private long id;

    private Date readTime;

    @OneToOne
    private Notice notice;

    @OneToOne
    private Believer believer;
}

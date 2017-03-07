package com.aifeng.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * Created by pro on 17-3-7.
 */
@Table
@Entity
public class User1 {

    public User1() {
        System.out.println("---------------------");
    }

    /*@Id
    @GeneratedValue*/
    private long id;

//    @Column
    private String name;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

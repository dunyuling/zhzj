package com.aifeng.jdbc;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by pro on 17-4-11.
 */
@Data
@NoArgsConstructor
public class Student {

    private long id;
    private String name;
    private int age;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

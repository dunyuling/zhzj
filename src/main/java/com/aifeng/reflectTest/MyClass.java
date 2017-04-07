package com.aifeng.reflectTest;

/**
 * Created by pro on 17-4-7.
 */
public class MyClass {
    private int count;
    public MyClass(int start) {
        count = start;
    }
    public void increase(int step) {
        count = count + step;
    }
}

package com.aifeng.reflectTest;

import jdk.internal.util.xml.impl.Pair;

import java.lang.reflect.*;
import java.util.List;

/**
 * Created by pro on 17-4-7.
 */
public class ReflectMain {

    public static void main(String[] args) {
        try {
            Constructor constructor = MyClass.class.getConstructor(int.class);
            MyClass myClassReflect = (MyClass) constructor.newInstance(10);
            Method method = MyClass.class.getMethod("increase", int.class);
            method.setAccessible(true);
            method.invoke(myClassReflect,5);
            Field field = MyClass.class.getField("count");
            field.setAccessible(true);
            System.out.println("Reflect -> " + field.getInt(myClassReflect));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
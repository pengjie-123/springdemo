package com.mt.annotaion;

import com.mt.bean.User;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class PickAnnotationTest {
    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("com.mt.bean.User");

        //get value of field in annotation
        Field[]  fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Pick pick = field.getAnnotation(Pick.class);
            if (pick == null) {continue;}
            String value = pick.level() + " : " + pick.note();
            System.out.println(value);
        }

        //get method and call it
        Method method = clazz.getDeclaredMethod("getInstance", int.class);
        User   o      = (User) method.invoke(clazz.newInstance(), 1);
       System.out.println(o);
        System.out.println("--------------------");
        // eate instance use constructor no parameter
        Object o1 = clazz.getConstructor().newInstance();
        System.out.println(o1);
        // create instance use constructor with parameter
        Constructor<?> constructor = clazz.getConstructor(Integer.class, String.class);
        o1 = constructor.newInstance(1, "hello");
        System.out.println(o1);

        //user private constructor
        Constructor<?> constructor1 = clazz.getDeclaredConstructor(String.class);
        constructor1.setAccessible(true);
        o1 = constructor1.newInstance("world");
        System.out.println(o1);

        System.out.println("---------------");
        //can only get public constructors
        for (Constructor m : clazz.getConstructors()) {
            System.out.println(m);
        }
        System.out.println("---------------");

        //get all declared constructors
        for (Constructor m : clazz.getDeclaredConstructors()) {
            System.out.println(m);
        }

    }
}

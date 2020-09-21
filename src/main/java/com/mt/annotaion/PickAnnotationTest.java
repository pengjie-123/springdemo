package com.mt.annotaion;

import com.mt.bean.User;
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

    }
}

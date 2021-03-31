package com.mt.bean;

import com.mt.annotaion.Pick;

public class User {
    @Pick(level = "vip1", note = "level higher, get more bonus")
    private Integer id;
    private String  name;

    public User() {
    }

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    private User(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override public String toString() {
        return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }

    public  User getInstance(int id) {
        return new User(id,"kevin");
    }

    private void init() {}
}
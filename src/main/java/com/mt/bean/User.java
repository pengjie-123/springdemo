package com.mt.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mt.annotaion.Pick;
import java.io.Serializable;
import org.springframework.web.bind.annotation.RequestParam;

public class User implements Serializable {
    @Pick(level = "vip1", note = "level higher, get more bonus")
    private Integer id;
    private String  name;

    public User() {
    }

    public User(Integer id, String name) {
        this.id = id.hashCode();
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
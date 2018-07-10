package com.airline.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by qxb-810 on 2018/7/10.
 */
@Component
public class TestEntity implements Serializable {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.exort.entity;

/**
 * This class represents the data in the table character
 * create table `character`(
 *     id int primary key auto_increment,
 *     name varchar(64) not null,
 *     area varchar(32),
 *     school varchar(128)
 * ) engine=InnoDB default charset=utf8;
 * @author NeilKleistGao
 * @version 1.0.0
 */
public class Character {
    private Integer id = null;
    private String name = null;
    private String area = null;
    private String school = null;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getArea() {
        return this.area;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSchool() {
        return school;
    }
}
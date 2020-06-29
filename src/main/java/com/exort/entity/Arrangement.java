package com.exort.entity;

import java.sql.Date;
import java.sql.Time;

/**
 * This class represents the data in the table arrangement
 * create table `arrangement`(
 *     id int primary key auto_increment,
 *     name varchar(64) not null,
 *     start_date Date not null,
 *     end_date Date not null,
 *     `repeat` int not null,
 *     start_time Time not null,
 *     end_time Time not null
 * ) engine=InnoDB default charset=utf8;
 *
 * @author NeilKleistGao
 * @version 1.0.0
 */
public class Arrangement {
    private Integer id = null;
    private String name;
    private Date start_date;
    private Date end_date;
    private Integer repeat;
    private Time start_time;
    private Time end_time;

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

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setRepeat(Integer repeat) {
        this.repeat = repeat;
    }

    public Integer getRepeat() {
        return repeat;
    }

    public void setStart_time(Time start_time) {
        this.start_time = start_time;
    }

    public Time getStart_time() {
        return start_time;
    }

    public void setEnd_time(Time end_time) {
        this.end_time = end_time;
    }

    public Time getEnd_time() {
        return end_time;
    }
}

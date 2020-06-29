package com.exort.entity;

/**
 * This class represents the data in the table settings
 * create table `settings`(
 *     setting_key varchar(64) primary key,
 *     setting_value varchar(128) not null
 * ) engine=InnoDB default charset=utf8;
 * @author NeilKleistGao
 * @version 1.0.0
 */
public class SettingsPair {
    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

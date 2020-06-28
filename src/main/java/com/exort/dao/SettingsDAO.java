package com.exort.dao;

import com.exort.entity.SettingsPair;

import java.util.List;

public interface SettingsDAO {
    public SettingsPair find(String key);
    public List<SettingsPair> find();
    public void update(String key, String value);
}

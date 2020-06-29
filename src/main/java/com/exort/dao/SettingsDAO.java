package com.exort.dao;

import com.exort.entity.SettingsPair;

import java.util.List;

/**
 * This interface determines all operations on the table settings in database
 * @author NeilKleistGao
 * @version 1.0.0
 * @see SettingsPair
 */
public interface SettingsDAO {
    /**
     * This method searches for the setting data whose key is given
     * @param key The given key
     * @return The setting data, or null if it's not found
     */
    public SettingsPair find(String key);

    /**
     * This method collects all setting objects in database
     * @return The list of all setting objects
     */
    public List<SettingsPair> find();

    /**
     * This method updates a specified setting object
     * @param key The key of this setting
     * @param value The new value of this setting
     */
    public void update(String key, String value);
}

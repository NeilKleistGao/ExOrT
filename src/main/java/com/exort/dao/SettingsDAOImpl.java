package com.exort.dao;

import com.exort.entity.SettingsPair;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @see com.exort.dao.BaseDAO
 * @see com.exort.dao.SettingsDAO
 * @author NeilKleistGao
 * @version 1.0.0
 */
public class SettingsDAOImpl extends BaseDAO implements SettingsDAO {
    @Override
    public SettingsPair find(String key) {
        List rows = this.getJdbcTemplate().queryForList("select * from `settings` where setting_key='" + key + "'");
        Iterator it = rows.iterator();

        if (it.hasNext()) {
            Map map = (Map)it.next();
            SettingsPair pair = new SettingsPair();

            pair.setKey(map.get("setting_key").toString());
            pair.setValue(map.get("setting_value").toString());

            return pair;
        }

        return null;
    }

    @Override
    public List<SettingsPair> find() {
        List rows = this.getJdbcTemplate().queryForList("select * from `settings`;");
        Iterator it = rows.iterator();
        List<SettingsPair> pairs = new LinkedList<>();

        while (it.hasNext()) {
            Map map = (Map)it.next();
            SettingsPair pair = new SettingsPair();

            pair.setKey(map.get("setting_key").toString());
            pair.setValue(map.get("setting_value").toString());

            pairs.add(pair);
        }

        return pairs;
    }

    @Override
    public void update(String key, String value) {
        this.getJdbcTemplate().update("update `settings` set setting_value=? where setting_key=?;", new Object[]{value, key});
    }
}

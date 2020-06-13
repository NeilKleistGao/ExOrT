package com.exort.dao;

import com.exort.entity.Character;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CharacterDAOImpl extends BaseDAO implements CharacterDAO {

    @Override
    public void insert(Character character) {
        int id = character.getId();
        String name = character.getName();
        String area = character.getName();
        String school = character.getSchool();

        String sql = "insert into character(id, name, area, school) values(?, ?, ?, ?)";
        this.getJdbcTemplate().update(sql, new Object[]{id, name, area, school});
    }

    @Override
    public Character find(Integer id) {
        List rows = this.getJdbcTemplate().queryForList("select * from character where id=" + id.intValue());
        Iterator it = rows.iterator();

        if (it.hasNext()) {
            Map map = (Map)it.next();
            Character character = new Character();

            character.setId(Integer.valueOf(map.get("id").toString()));
            character.setName(map.get("name").toString());
            character.setArea(map.get("area").toString());
            character.setSchool(map.get("school").toString());
        }

        return null;
    }
}

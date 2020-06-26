package com.exort.dao;

import com.exort.entity.Character;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CharacterDAOImpl extends BaseDAO implements CharacterDAO {

    @Override
    public void insert(Character character) {
        String name = character.getName();
        String area = character.getArea();
        String school = character.getSchool();

        String sql = "insert into `character`(name, area, school) values(?, ?, ?)";
        this.getJdbcTemplate().update(sql, new Object[]{name, area, school});
    }

    @Override
    public Character find(Integer id) {
        if (id == null) {
            return null;
        }

        List rows = this.getJdbcTemplate().queryForList("select * from `character` where id=" + id.toString());
        Iterator it = rows.iterator();

        if (it.hasNext()) {
            Map map = (Map)it.next();
            Character character = new Character();

            character.setId(Integer.valueOf(map.get("id").toString()));
            character.setName(map.get("name").toString());
            if (map.get("area") == null) {
                character.setArea("");
            }
            else {
                character.setArea(map.get("area").toString());
            }
            if (map.get("school") == null) {
                character.setSchool("");
            }
            else {
                character.setSchool(map.get("school").toString());
            }

            return character;
        }

        return null;
    }

    @Override
    public List<Character> find() {
        List<Character> characters = new LinkedList<>();
        List rows = this.getJdbcTemplate().queryForList("select * from `character`;");
        Iterator it = rows.iterator();

        while (it.hasNext()) {
            Map map = (Map)it.next();
            Character character = new Character();

            character.setId(Integer.valueOf(map.get("id").toString()));
            character.setName(map.get("name").toString());

            if (map.get("area") == null) {
                character.setArea("");
            }
            else {
                character.setArea(map.get("area").toString());
            }
            if (map.get("school") == null) {
                character.setSchool("");
            }
            else {
                character.setSchool(map.get("school").toString());
            }

            characters.add(character);
        }

        return characters;
    }

    @Override
    public void update(Character character) {
        String sql = "update `character` set name=?, area=?, school=? where id=?;";
        String name = character.getName();
        String area = character.getArea();
        String school = character.getSchool();
        int id = character.getId().intValue();

        this.getJdbcTemplate().update(sql, new Object[] {name, area, school, id});
    }

    @Override
    public void delete(Integer id) {
        String sql = "delete from `character` where id=" + id.toString();
        this.getJdbcTemplate().update(sql);
    }
}

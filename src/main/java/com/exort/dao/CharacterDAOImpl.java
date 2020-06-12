package com.exort.dao;

import com.exort.entity.Character;
import org.springframework.jdbc.core.JdbcTemplate;

public class CharacterDAOImpl extends BaseDAO implements CharacterDAO {

    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }

    @Override
    public void insert(Character character) {

    }

    @Override
    public Character find(int id) {
        return null;
    }
}

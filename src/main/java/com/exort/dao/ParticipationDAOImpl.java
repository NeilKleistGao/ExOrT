package com.exort.dao;

import com.exort.entity.Participation;
import org.springframework.jdbc.core.JdbcTemplate;

public class ParticipationDAOImpl extends BaseDAO implements ParticipationDAO {

    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }

    @Override
    public void insert(Participation participation) {

    }

    @Override
    public Participation find(int id) {
        return null;
    }
}

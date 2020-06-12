package com.exort.dao;

import com.exort.entity.Arrangement;
import org.springframework.jdbc.core.JdbcTemplate;

public class ArrangementDAOImpl extends BaseDAO implements ArrangementDAO {
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }

    @Override
    public void insert(Arrangement arrangement) {

    }

    @Override
    public Arrangement find(int id) {
        return null;
    }
}

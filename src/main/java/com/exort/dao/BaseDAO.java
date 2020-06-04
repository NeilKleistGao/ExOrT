package com.exort.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class BaseDAO {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}

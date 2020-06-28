package com.exort.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * This class is the basic class of all data access object class
 * @author NeilKleistGao
 * @version 1.0.0
 */
public class BaseDAO {
    private DataSource dataSource;

    /**
     * This method return the jdbc template object
     * @return The jdbc template object
     */
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }

    /**
     * This method allows user to set data source
     * @param dataSource The data source object
     */
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * This method allows user to get data source
     * @return The data source object
     */
    public DataSource getDataSource() {
        return dataSource;
    }

    /**
     * This method allows user to get connection object
     * @return The connection object
     */
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

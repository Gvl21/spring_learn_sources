package com.busanit.spring.f_db.config;

import org.apache.tomcat.jdbc.pool.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBQuery {
    private DataSource dataSource;

    public DBQuery(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int count() {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM MEMBER");
            resultSet.next();
            return resultSet.getInt(1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e){
                throw new RuntimeException(e);
            }
        }

    }

}

package com.pistofranco.roleplay;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jordi on 03/03/2017.
 */
public class MySQL {
    private static DataSource datasource;
    public static DataSource getDataSource()
    {
        if(datasource == null) {
            HikariConfig config = new HikariConfig();
            config.setMaximumPoolSize(100);

            config.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
            config.addDataSourceProperty("serverName", "localhost");
            config.addDataSourceProperty("port", "3306");
            config.addDataSourceProperty("databaseName", "bukkit");
            config.addDataSourceProperty("user", "root");
            config.addDataSourceProperty("password", "root");

            System.out.print("qq");

            datasource = new HikariDataSource(config);
        }
        return datasource;
    }
    public void stabilishConnection(){
        Connection connection = null;
        PreparedStatement statement;
        ResultSet resultSet;
        try
        {
            DataSource dataSource = getDataSource();
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT * FROM account");
            System.out.println("The Connection Object is of Class: " + connection.getClass());
            resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                System.out.println(resultSet.getString(1) + "," + resultSet.getString(2) + "," + resultSet.getString(3));
            }
        }
                catch (Exception e)
        {
            try
            {
                connection.rollback();
            }
                        catch (SQLException e1)
            {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}

package com.cskaoyan.mail.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author 史栋林
 * @date 2020/8/5 16:56
 */
public class DruidUtils {

    private static DataSource dataSource;

    static {

        try {
            Properties properties = new Properties();
            InputStream inputStream = DruidUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            properties.load(inputStream);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }
    public static DataSource getDataSource(){
        return dataSource;
    }
}

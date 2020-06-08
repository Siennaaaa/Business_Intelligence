package com.example.demo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MongoDBConn {
    private  static Connection conn;

    public Connection getMongoDBConn() throws ClassNotFoundException, SQLException {
        if (conn.isClosed()&&conn==null){
             Class.forName(ConfigUtils.mysqlDriver);
             conn = DriverManager.getConnection(ConfigUtils.mysqlUrl, ConfigUtils.mysqlUser, ConfigUtils.mysqlPassword);
        }
        return conn;
    }


}

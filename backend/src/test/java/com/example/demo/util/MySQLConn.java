package com.example.demo.util;

import java.sql.*;
import java.util.ArrayList;

public class MySQLConn {
    private  static Connection conn;

    public Connection getMysqlConn() throws ClassNotFoundException, SQLException {
        if (conn.isClosed()&&conn==null){
            Class.forName(ConfigUtils.mysqlDriver);
            conn = DriverManager.getConnection(ConfigUtils.mysqlUrl, ConfigUtils.mysqlUser, ConfigUtils.mysqlPassword);
        }
        return conn;
    }

    //通过节点的类型和名字进行模糊查询
    public ArrayList<CacheEntity> selectNodesByTypeandName(int type,String name) throws SQLException, ClassNotFoundException {
        String sql = "select * from Business where Type=? and Name like ? limit 50";

        PreparedStatement preparedStatement = getMysqlConn().prepareStatement(sql);
        preparedStatement.setString(1, NodeUtils.getTypeFromKey(type).getKey());
        preparedStatement.setString(2, name+"%");

        //得到查询结果
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<CacheEntity> cacheEntities = new ArrayList<>();
        while (resultSet.next()){
            cacheEntities.add(new CacheEntity(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));
            System.out.println(resultSet.getInt(1)+resultSet.getString(2)+resultSet.getString(3));
        }
        preparedStatement.close();

        if (!conn.isClosed())
            conn.close();
        return cacheEntities;
    }
}

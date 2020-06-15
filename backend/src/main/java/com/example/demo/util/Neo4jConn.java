package com.example.demo.util;

import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.GraphDatabase;

import org.neo4j.driver.v1.Driver;

public class Neo4jConn {
    private Driver driver;
    private Session session;

    //连接neo4j
    private Driver getNeo4jConn(){
        driver = GraphDatabase.driver(ConfigUtils.getUrl(), AuthTokens.basic(ConfigUtils.username,ConfigUtils.password));
        return driver;
    }

    //关闭连接
    private void close(){
        session.close();
        driver.close();
    }

    //获取绘画
    public Session getSession(){
        if (session!=null&&session.isOpen())
        {
            Driver d = getNeo4jConn();
            session = d.session();
        }
        return session;
    }
}

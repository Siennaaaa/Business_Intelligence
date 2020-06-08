package com.example.demo.util;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Node;
import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class MongoDBConn {

    private static MongoClient mongoClient;

    private static MongoClient getMongoClient() {
        if (mongoClient == null){
            MongoCredential credential =  MongoCredential.createCredential(ConfigUtils.mongoUser, ConfigUtils.mongoDatabase, ConfigUtils.mongoPassword.toCharArray());
            mongoClient = new MongoClient(new ServerAddress(ConfigUtils.mongoIp, ConfigUtils.mongoPort), credential, MongoClientOptions.builder().sslEnabled(false).build());
        }
        return mongoClient;
    }

    /**
     *根据查询的类型确定数据库
     * @param type
     * @return
     */
    private static MongoCollection<Document> getMongoCollection(int type){
        switch (type){
            case ConfigUtils.mongoSingleCollectionType:
                return getMongoClient().getDatabase(ConfigUtils.mongoDatabase).getCollection(ConfigUtils.mongoSingleCollection);
            case ConfigUtils.mongoDoubleCollectionType:
                return getMongoClient().getDatabase(ConfigUtils.mongoDatabase).getCollection(ConfigUtils.mongoDoubleCollection);
            case ConfigUtils.mongoAllMinPathsCollectionType:
                return getMongoClient().getDatabase(ConfigUtils.mongoDatabase).getCollection(ConfigUtils.mongoAllMinPathCollection);
            default:
                return getMongoClient().getDatabase(ConfigUtils.mongoDatabase).getCollection(ConfigUtils.mongoMinPathCollection);
        }
    }

    /**
     * 向缓存数据库mongoDB中插入节点类型，例如如果
     * @param param 检索词
     * @param hashMap key-value：检索词- 时间+检索结果
     * @param type 节点类型
     */
    public static void addOne(String param, HashMap<String, ArrayList<Node>> hashMap, int type){
        //获取节点类型对应的数据库
        MongoCollection<Document> collection = getMongoCollection(type);
        JSONObject jsonObject = new JSONObject();
        jsonObject.putAll(hashMap);
        Document document = new Document("id", param).append("time", TimeUtils.getCurrentTime()).append("result", jsonObject.toJSONString());
        //向数据库中插入数据
        collection.insertOne(document);
    }

    /**
     * 根据Node的类型确定数据库，然后在数据库中查询这个id
     * @param param
     * @param type
     * @return
     */
    public static String findOne(String param, int type){
        //根据Node的类型确定数据库
        MongoCollection<Document> collection = getMongoCollection(type);
        MongoCursor<Document> cursor = collection.find(Filters.eq("id", param)).iterator();
        if (cursor.hasNext()){
            System.err.println("从MongoDB中查[" + "type:" + type + ",param:" + param + "]");
            JSONObject jsonObject = JSONObject.parseObject(cursor.next().get("result").toString());
            if (jsonObject != null) {
                return jsonObject.toJSONString();
            }
        }
        System.err.println("从Neo4j中查[" + "type:" + type + ",param:" + param + "]");
        return null;
    }

    /**
     * 依据Node的类型
     * @param type
     * @return
     */
    public static ArrayList<HashMap<String, Object>> getHistory(int type){
        ArrayList<HashMap<String, Object> > result = new ArrayList<>();
        MongoCollection<Document> collection = getMongoCollection(type);
        FindIterable<Document> documents = collection.find().limit(ConfigUtils.mongoMaxHistories).sort(new BasicDBObject("time", -1));
        for (Document document : documents){
            if (document.get("result") != null && !document.get("result").toString().equals("")){
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("id", document.get("id"));
                hashMap.put("time", document.get("time"));
                hashMap.put("result", document.get("result"));
                result.add(hashMap);
            }
        }
        return result;
    }





}

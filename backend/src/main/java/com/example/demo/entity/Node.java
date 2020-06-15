package com.example.demo.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Neo4j中的节点类型，可序列化
 */
public class Node implements Serializable {
    //节点是图标的基本单位，它包含具有键值对对属性，因此这里引用一个hashMap来存储这个节点的一组组属性
    public HashMap<String, Object> properties;

    public Node() {
        properties = new HashMap<>();
    }

    public Node(HashMap<String, Object> properties) {
        this.properties = properties;
    }

    public HashMap<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(HashMap<String, Object> properties) {
        this.properties = properties;
    }

    //获取属性
    public Object get(String key){
        return properties.get(key);
    }

    //添加属性
    public void put(String key, Object o){
        properties.put(key, o);
    }

    public void putAll(Map<String, Object> map){
        properties.putAll(map);
    }

    @Override
    public String toString(){
        return getProperties().toString();
    }

    /**
     * NodeEntity的相等需要Node的id相等即可，用于在List中添加时过滤相同节点
     * @param object 比较对象
     * @return 是否相等
     */
    @Override
    public boolean equals(Object object){
        if (object == null)
            return false;
        else if (!(object instanceof Node))
            return false;
        return getProperties().get("id").equals(((Node) object).getProperties().get("id"));
    }
}

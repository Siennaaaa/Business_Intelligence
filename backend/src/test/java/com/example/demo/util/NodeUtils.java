package com.example.demo.util;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public class NodeUtils {
    private static HashMap<Integer, Pair<String, String>> nodeTypes = null;

    private static ArrayList<String> propertyNodes;

    public static HashMap<Integer, Pair<String, String>> getNodeTypes(){
        if (nodeTypes == null){
            nodeTypes = new HashMap<>();
            initNodeTypes();
        }
        return nodeTypes;
    }

    public static ArrayList<String> getPropertyNodes(){
        if (propertyNodes == null){
            propertyNodes = new ArrayList<>();
            initNodeTypes();
        }
        return propertyNodes;
    }

    private static void initPropertyNode(){
        propertyNodes.add("ns0__Officership");
        propertyNodes.add("ns0__TenureInOrganization");
        propertyNodes.add("ns0__OfficerRole");
        propertyNodes.add("ns0__DirectorRole");
        propertyNodes.add("ns0__Directorship");
        propertyNodes.add("Resource");
    }


    //初始化节点的类型
    // 宿舍服务器节点label名称
    private static void initNodeTypes() {
        nodeTypes.put(1, new Pair<>("ns4__Quote","ns1__hasName"));
        nodeTypes.put(2, new Pair<>("ns7__Organization","ns6__organization-name"));
        nodeTypes.put(3, new Pair<>("ns4__Instrument","ns1__hasName"));
        nodeTypes.put(4, new Pair<>("ns4__AssetClass","rdfs__label"));
        nodeTypes.put(5, new Pair<>("ns6__Currency","skos__prefLabel"));
        nodeTypes.put(6, new Pair<>("ns6__CurrencySubunit","skos__prefLabel"));
        nodeTypes.put(7, new Pair<>("ns5__Activity","skos__prefLabel"));
        nodeTypes.put(8, new Pair<>("ns5__BusinessSector","skos__prefLabel"));
        nodeTypes.put(9, new Pair<>("ns5__EconomicSector","skos__prefLabel"));
        nodeTypes.put(10, new Pair<>("ns5__Industry","skos__prefLabel"));
        nodeTypes.put(11, new Pair<>("ns0__AcademicQualification","ns8__fromInstitutionName"));
        nodeTypes.put(12, new Pair<>("ns0__Person","ns6__given-name"));
        nodeTypes.put(13, new Pair<>("ns5__IndustryGroup","skos__prefLabel"));
        nodeTypes.put(14, new Pair<>("ns0__Major","skos__prefLabel"));
        nodeTypes.put(15, new Pair<>("ns0__AcademicDegree","skos__prefLabel"));
    }

    public static Pair<String, String> getTypeFromKey(int key){
        return getNodeTypes().get(key);
    }
}

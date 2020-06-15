package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.NodeEntity;
import com.example.demo.service.GraphService;
import com.example.demo.util.ParamUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class GraphController {


    /**
     *
     *通过一个实体查询其关联的所有关系和实体(限定跳数和结果数量)
     * @param type
     * @param step
     * @param id
     * @param limit
     * @return
     */
    @PostMapping("api/queryEntity")
    @ResponseBody
    public String queryEntity(@RequestParam("type") int type, @RequestParam("step") int step, @RequestParam("id") int id, @RequestParam("limit") int limit){
        step = ParamUtils.checkStep(step);
        limit = ParamUtils.checkLimit(limit);
        String param = ParamUtils.paramsToString(type, step, id, limit);
        HashMap<String, ArrayList<NodeEntity>> hashMap = new GraphService().searchByTypeAndId(type, step, limit, id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.putAll(hashMap);
        String results = jsonObject.toJSONString();

        return results;

    }

}

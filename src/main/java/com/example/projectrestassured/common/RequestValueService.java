package com.example.projectrestassured.common;

import com.example.projectrestassured.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import java.util.*;

import org.springframework.stereotype.Service;

/***
 * 接口调用参数处理类
 *
 */
@Service
@Slf4j
public class RequestValueService {

    /**
     * 将json格式的参数转换成map格式
     */
    public static Map getRequestParams(String params) throws Exception {
        Map<String, Object> paramsMap = new HashMap<>();
        //循环读取测试case
        JSONObject jsonPath = new JSONObject(params);
        Stack<JSONObject> stObj = new Stack<JSONObject>();
        stObj.push(jsonPath);
        paramsMap = new HashMap<String, Object>();
        JsonToMap(stObj, paramsMap);
        return paramsMap;
    }

    /**
     * 将json格式的键值对转换成map的方法
     *
     * @param stObj
     * @param paramsMap
     * @throws Exception
     */
    public static void JsonToMap(Stack<JSONObject> stObj, Map<String, Object> paramsMap) throws Exception {
        if (stObj == null && stObj.pop() == null) {
            return;
        }
        JSONObject json = stObj.pop();
        Iterator it = json.keys();
        while (it.hasNext()) {
            String key = (String) it.next();
            //得到value的值
            Object value = json.get(key);
            //System.out.println(value);
            if (value instanceof JSONObject) {
                stObj.push((JSONObject) value);
                //递归遍历
                JsonToMap(stObj, paramsMap);
            } else {
                paramsMap.put(key, value);
            }
        }
    }
}

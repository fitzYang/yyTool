/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.lang;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

import static com.alibaba.fastjson.serializer.SerializerFeature.DisableCircularReferenceDetect;

/**
 * <p> 在FastJSON的基础上对其一些常用的功能进行工具化支持 </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
public class JsonUtil {


    public static final TypeReference<Map<String, JSONObject>> MAP_JSONOBJECT_TR = new TypeReference<Map<String,
            JSONObject>>() {
    };
    public static final TypeReference<Map<String, Object>>     MAP_OBJECT_TR     = new TypeReference<Map<String,
            Object>>() {
    };
    public static final TypeReference<Map<String, String>>     MAP_STRING_TR     = new TypeReference<Map<String,
            String>>() {
    };
    public static final TypeReference<Map<String, JSONArray>>  MAP_JSONARRAY_TR  = new TypeReference<Map<String, JSONArray>>() {
    };


    private JsonUtil() {
    }


    /**
     * JSON转换的时候不进行ref处理
     *
     * @param object 要处理的对象
     * @return 字符串
     */
    public static String toJsonWithoutRef(Object object) {
        return JSON.toJSONString(object, DisableCircularReferenceDetect);
    }

    /**
     * json字符串转化为map
     *
     * @param jsonStr json 字符串
     * @return 转换的Map
     */
    public static Map<String, Object> toMap(String jsonStr) {
        return JSONObject.parseObject(jsonStr, MAP_OBJECT_TR);
    }

    /**
     * json字符串转化为map
     *
     * @param jsonStr json 字符串
     * @return 转换的Map
     */
    public static Map<String, JSONObject> toJOMap(String jsonStr) {
        return JSONObject.parseObject(jsonStr, MAP_JSONOBJECT_TR);
    }
}

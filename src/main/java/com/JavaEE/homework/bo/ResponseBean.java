package com.JavaEE.homework.bo;

import java.util.HashMap;
import java.util.Map;

public class ResponseBean {

    private Map<String, Object> responseMap = new HashMap<>();
    private static final Integer success = 0;
    private static final Integer error = -1;
    private static final String message = "ok";

    // 默认应答成功
    public ResponseBean() {
        responseMap.put("status", success);
        responseMap.put("message", message);
    }

    // 出错时消息
    public void putError(String message) {
        responseMap.put("status", error);
        responseMap.put("message", message);
    }

    // 数据
    public void putData(String key, Object value) {
        responseMap.put(key, value);
    }

    // 分页数据
    public void putPageData(Object data, Object count){
        responseMap.put("code", success);
        responseMap.put("data", data);
        responseMap.put("count", count);
    }

    public Map<String, Object> getResponseMap() {
        return responseMap;
    }

}

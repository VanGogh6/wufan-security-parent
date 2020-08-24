package com.wufan.base.result;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * 自定义响应结构
 * @author wufan
 * @date 2020/4/11 0011 2:37
 */
@Data
public class MyResult {

    // 响应业务状态
    private Integer code;

    // 响应消息
    private String message;

    // 响应中的数据
    private Object data;

    public MyResult() {
    }
    public MyResult(Object data) {
        this.code = 200;
        this.message = "OK";
        this.data = data;
    }
    public MyResult(String message, Object data) {
        this.code = 200;
        this.message = message;
        this.data = data;
    }

    public MyResult(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static MyResult ok() {
        return new MyResult(null);
    }
    public static MyResult ok(String message) {
        return new MyResult(message, null);
    }
    public static MyResult ok(Object data) {
        return new MyResult(data);
    }
    public static MyResult ok(String message, Object data) {
        return new MyResult(message, data);
    }

    public static MyResult build(Integer code, String message) {
        return new MyResult(code, message, null);
    }

    public static MyResult build(Integer code, String message, Object data) {
        return new MyResult(code, message, data);
    }

    public String toJsonString() {
        return JSON.toJSONString(this);
    }


    /**
     * JSON字符串转成 MengxueguResult 对象
     * @param json
     * @return
     */
    public static MyResult format(String json) {
        try {
            return JSON.parseObject(json, MyResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

package com.example.knowledgegraph.response;

import lombok.Data;

import java.util.HashMap;

@Data
public class Result extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    /**
     * 初始化一个新创建的 Message 对象
     */
    public Result() {

    }

    /**
     * 返回错误消息
     *
     * @return 错误消息
     */
    public static Result error() {
        return error("1", "操作失败");
    }

    public static Result error(BaseErrorEnum baseErrorEnum) {
        return error(baseErrorEnum.getCode(), baseErrorEnum.getMessage());
    }

    /**
     * 返回错误消息
     *
     * @param msg 内容
     * @return 错误消息
     */
    public static Result error(String msg) {
        return error("500", msg);
    }

    /**
     * 返回错误消息
     *
     * @param code 错误码
     * @param msg 内容
     * @return 错误消息
     */
    public static Result error(String code, String msg) {
        Result json = new Result();
        json.put("code", code);
        json.put("msg", msg);
        return json;
    }

    /**
     * 返回成功消息
     *
     * @param msg 内容
     * @return 成功消息
     */
    public static Result success(String msg) {
        Result json = new Result();
        json.put("code", BaseErrorEnum.SUCCESS.getCode());
        json.put("msg", msg);
        return json;
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static Result success() {
        return Result.success("操作成功");
    }

    public static Result successData(Object value){
        Result json = new Result();
        json.put("code", BaseErrorEnum.SUCCESS.getCode());
        json.put("msg", "success");
        json.put("data", value);
        return json;
    }

    /**
     * 返回项目基本信息
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public static Result successProjectInfoData(int code, String msg, Object data){
        Result json = new Result();
        json.put("code", BaseErrorEnum.SUCCESS.getCode());
        json.put("msg", msg);
        json.put("data", data);
        return json;
    }

    /**
     * 返回成功消息
     * @param key 键值
     * @param value 内容
     * @return 成功消息
     */
    @Override
    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}

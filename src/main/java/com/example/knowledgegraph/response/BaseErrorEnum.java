package com.example.knowledgegraph.response;

public enum BaseErrorEnum implements BaseErrorInfoInterface{

    //定义异常枚举值
    SUCCESS( "200", "success"),
    BODY_NOT_MATCH("400" , "数据格式不匹配"),
    NOT_FOUND("404", "访问资源不存在"),
    INTERNAL_SERVER_ERROR("500", "服务器内部错误"),
    SERVER_BUSY("503", "服务器正忙"),
    REQUEST_METHOD_SUPPORT_ERROR("10001", "当前请求方法不支持"),
    REQUEST_DATA_NULL("10002", "当前请求参数为空");

    private String code;
    private String message;

    BaseErrorEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode(){
        return code;
    }

    @Override
    public String getMessage(){
        return message;
    }
}

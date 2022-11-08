//package com.example.knowledgegraph.response;
//
//public class RespGenerator {
//
//    //正常返回时调用方法
//    public static BaseResponse success(Object data) {
//        return new BaseResponse(BaseErrorEnum.SUCCESS.getCode(), "接口调用成功!", data);
//    }
//    //失败时调用方法（入参是异常枚举)
//    public static BaseResponse<Object> fail(BaseErrorEnum baseErrorEnum) {
//        return new BaseResponse<Object>(baseErrorEnum.getCode(), baseErrorEnum.getMessage(), null);
//    }
////调用失败(提供给GlobalExceptionHandler类使用)
//    public static BaseResponse<Object> fail(String code, String message){
//        return new BaseResponse<Object>(code, message, null);
//    }
//}

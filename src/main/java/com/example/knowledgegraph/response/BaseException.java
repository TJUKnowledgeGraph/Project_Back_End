package com.example.knowledgegraph.response;

public class BaseException extends RuntimeException{

    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BaseException(){
        super();
    }

    public BaseException(BaseErrorEnum baseErrorEnum){
        super(baseErrorEnum.getCode());
        this.code = baseErrorEnum.getCode();
        this.message = baseErrorEnum.getMessage();
    }
}

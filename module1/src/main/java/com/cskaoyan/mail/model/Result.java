package com.cskaoyan.mail.model;

/**
 * @author 史栋林
 * @date 2020/8/5 20:42
 */
public class Result {
    private Integer code;

    private String message;

    private Object data;

    public Result(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result() {
    }

    public static Result ok(){
        return new Result(0,null,null);
    }

    public static Result ok(Object data){
        return new Result(0,null,data);
    }

    public static Result error(String message){
        return new Result(10000,message,null);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}

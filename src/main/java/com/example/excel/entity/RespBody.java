package com.example.excel.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@ToString
public class RespBody<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    // 0:成功;否则失败
    private int code;
    private T data;
    private String msg;

    private RespBody(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static <T> RespBody<T> data(T data) {
        return new RespBody(0, data, "调用成功");
    }

    public static <T> RespBody<T> fail(String msg) {
        return new RespBody(-1, null,msg);
    }

    public static <T> RespBody<T> fail(int code, String msg) {
        return new RespBody(code, null, msg);
    }

    public static <T>  Boolean isSuccess(RespBody<T> rb){
        return rb!=null && 0 == rb.code;
    }
}

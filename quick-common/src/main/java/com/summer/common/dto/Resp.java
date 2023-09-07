package com.summer.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.summer.common.constants.DapConstants;
import com.summer.common.exceptions.DapException;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description:
 * @author: chenhao
 * @date: 2023-7-20 10:21
 */
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Resp<T> implements Serializable{
    @Getter
    @Setter
    private Integer code;
    @Getter
    @Setter
    private String msg;
    @Getter
    @Setter
    private T data;

    /**
     * 构造返回数据
     * @param data
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    private static <T> Resp<T> createResult(T data, int code, String msg) {
        Resp<T> apiResult = new Resp<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

    /**
     * 无参的成功
     * @param <T>
     * @return
     */
    public static <T> Resp<T> ok(){
        return createResult(null, DapConstants.SUCCESS, DapConstants.SUCCESS_DESC);
    }

    /**
     * 无返回体的成功
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> Resp<T> ok(int code,String msg){
        return createResult(null, code,msg);
    }

    /**
     * 完整的返回
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> Resp<T> ok(T data,int code,String msg){
        return createResult(data, code,msg);
    }

    /**
     * 完整的返回
     * @param <T>
     * @return
     */
    public static <T> Resp<T> ok(T data){
        return createResult(data, DapConstants.SUCCESS, DapConstants.SUCCESS_DESC);
    }

    /**
     *无参的
     * @return
     */
    public static <T> Resp<T> failed(){
        return  createResult(null, DapConstants.FAILED, DapConstants.FAILED_DESC);
    }

    /**
     * 自定义返回参数的
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> Resp<T> failed(int code,String msg){
        return createResult(null,code,msg);
    }

    /**
     * 全参返回
     * @param data
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> Resp<T> failed(T data,int code,String msg){
        return createResult(data,code,msg);
    }

    /**
     * 可能出错时候想返回点什么
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Resp<T> failed(T data){
        return createResult(data, DapConstants.FAILED, DapConstants.FAILED_DESC);
    }

    /**
     * 通过自定义报错类型返回实体
     * @param e
     * @param <T>
     * @return
     */
    public static <T> Resp<T> failed(DapException e){
        return createResult(null,e.getErrCode(),e.getMessage());
    }

    /**
     * 可能报错的时候还想返回点啥
     * @param data
     * @param e
     * @param <T>
     * @return
     */
    public static <T> Resp<T> failed(T data,DapException e){
        return createResult(data,e.getErrCode(),e.getMessage());
    }
}

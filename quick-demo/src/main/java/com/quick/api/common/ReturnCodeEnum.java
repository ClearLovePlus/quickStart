package com.quick.api.common;

import com.summer.common.exceptions.DapException;
/**
 * @description: 返回值枚举类
 * @author: chenhao
 * @date: 2023-7-20 14:51
 */
public enum ReturnCodeEnum {
    PARAMETER_ERROR(10000, "参数错误"),
    PUSH_TYPE_ERROR(10001, "推送类型不存在，请检查配置"),
    PUSH_TITLE_ERROR(10002, "推送表头为空，请检查配置"),
    PUSH_AUTH_ERROR(10003, "权限校验：typeCode不能为空"),
    PUSH_AUTH_MOBILE_OA_ERROR(10004, "权限校验:phoneNum或者oaAccount为空"),
    PUSH_AUTH_NO_PERMISSION(10005, "权限校验：无该推送项目权限,请联系管理员");
    private final Integer code;
    private final String msg;

    ReturnCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public Integer getCode() {
        return code;
    }


    public String getMsg() {
        return msg;
    }

    /**
     * 转换为可抛出的错误
     *
     * @param returnCodeEnum
     * @return
     */
    public static DapException throwDapException(ReturnCodeEnum returnCodeEnum) {
        return new DapException(returnCodeEnum.getCode(), returnCodeEnum.getMsg());
    }

}

package com.summer.common.exceptions;

import lombok.Getter;

/**
 * @description: 基础异常类
 * @author: chenhao
 * @date: 2023-7-20 10:58
 */
@Getter
public class DapException extends Exception {
    private final Integer errCode;

    public DapException(Integer errCode, String msg) {
        super(msg);
        this.errCode = errCode;
    }

    public DapException(Integer errCode, Throwable t) {
        super(errCode.toString(), t);
        this.errCode = errCode;
    }

    public DapException(Integer errCode, String msg, Throwable t) {
        super(msg, t);
        this.errCode = errCode;
    }
}

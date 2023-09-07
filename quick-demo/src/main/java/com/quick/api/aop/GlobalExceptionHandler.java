package com.quick.api.aop;

import com.summer.common.dto.Resp;
import com.summer.common.exceptions.DapException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description: 全局异常处理，以throw exception方式处理异常
 * @author: chenhao
 * @date: 2021-1-7 14:09
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 处理业务异常
     *
     * @param be
     * @return
     */
    @ExceptionHandler(DapException.class)
    @ResponseBody
    public Resp businessException(DapException be) {
        log.info(String.format("业务异常:%s", be.getMessage()), be);
        return Resp.failed(be.getErrCode(),be.getMessage());
    }

}

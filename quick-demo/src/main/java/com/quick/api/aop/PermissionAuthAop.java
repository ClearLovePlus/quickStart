package com.quick.api.aop;

import com.quick.api.annotations.PermissionAuth;
import com.quick.api.config.DapPushConfig;
import com.quick.api.repository.mapper.po.TPushType;
import com.quick.api.repository.mapper.po.TPushWhiteList;
import com.quick.api.service.IPushTypeService;
import com.quick.api.service.IPushWhiteListService;
import com.summer.common.util.ArrayUtils;
import com.quick.api.common.ReturnCodeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @description: 权限控制aop
 * @author: chenhao
 * @date: 2023-7-20 18:25
 */
@Aspect
@Order(10)
@Component
@Slf4j
@RequiredArgsConstructor
public class PermissionAuthAop {
    private final IPushTypeService pushTypeService;
    private final IPushWhiteListService pushWhiteListService;
    private final DapPushConfig dapPushConfig;
    private static final String TYPE_CODE = "typeCode";
    private static final String OA_ACCOUNT = "oaAccount";
    private static final String PHONE_NUM = "phoneNum";

    /**
     * 只对加上这个注解的接口进行权限控制
     */
    @Pointcut("@annotation(com.quick.api.annotations.PermissionAuth)")
    public void permissionAuthAop() {

    }

    @Around("permissionAuthAop()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        PermissionAuth annotation = methodSignature.getMethod().getAnnotation(PermissionAuth.class);
        String[] parameterNames = methodSignature.getParameterNames();
        Object[] args = point.getArgs();
        int typeCodeIndex = ArrayUtils.indexOf(parameterNames, TYPE_CODE);
        int oaAccountIndex = ArrayUtils.indexOf(parameterNames, OA_ACCOUNT);
        int phoneNumIndex = ArrayUtils.indexOf(parameterNames, PHONE_NUM);
        String typeCode = (String) args[typeCodeIndex];
        if (StringUtils.isBlank(typeCode)) {
            throw ReturnCodeEnum.throwDapException(ReturnCodeEnum.PUSH_AUTH_ERROR);
        }
        //增加权限过滤配置
        String dapIgnoreCode = dapPushConfig.getDapIgnoreCode();
        if(StringUtils.isNotBlank(dapIgnoreCode)){
            log.info("ignoreCode is:{}",dapIgnoreCode);
            String[] split = dapIgnoreCode.split(";");
            List<String> ignoreCodes = Arrays.asList(split);
            if(ignoreCodes.contains(typeCode)){
                return point.proceed();
            }
        }
        TPushType pushType = pushTypeService.getByPushCode(typeCode);
        if (pushType == null) {
            throw ReturnCodeEnum.throwDapException(ReturnCodeEnum.PUSH_TYPE_ERROR);
        }
        if (oaAccountIndex == ArrayUtils.INVALID_INDEX && phoneNumIndex == ArrayUtils.INVALID_INDEX) {
            throw ReturnCodeEnum.throwDapException(ReturnCodeEnum.PUSH_AUTH_MOBILE_OA_ERROR);
        }
        if (StringUtils.isBlank((String) args[oaAccountIndex]) && StringUtils.isBlank((String) args[phoneNumIndex])) {
            throw ReturnCodeEnum.throwDapException(ReturnCodeEnum.PUSH_AUTH_MOBILE_OA_ERROR);
        }
        if (oaAccountIndex != ArrayUtils.INVALID_INDEX&&!StringUtils.isBlank((String) args[oaAccountIndex])){
            TPushWhiteList byOaAccountAndTypeId = pushWhiteListService.getByOaAccountAndTypeId((String) args[oaAccountIndex], pushType.getId());
            if (byOaAccountAndTypeId == null) {
                throw ReturnCodeEnum.throwDapException(ReturnCodeEnum.PUSH_AUTH_NO_PERMISSION);
            } else {
                return point.proceed();
            }
        }
        TPushWhiteList phoneWhiteList = pushWhiteListService.getByMobileAndTypeId((String) args[phoneNumIndex], pushType.getId());
        if (phoneWhiteList == null) {
            throw ReturnCodeEnum.throwDapException(ReturnCodeEnum.PUSH_AUTH_NO_PERMISSION);
        } else {
            return point.proceed();
        }
    }
}

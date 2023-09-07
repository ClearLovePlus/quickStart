package com.quick.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.quick.api.config.DapPushConfig;
import com.quick.api.repository.TPushTypeRepository;
import com.quick.api.repository.TPushWhiteListRepository;
import com.quick.api.repository.mapper.po.TPushType;
import com.quick.api.repository.mapper.po.TPushWhiteList;
import com.quick.api.dto.OaUser;
import com.quick.api.service.IPushWhiteListService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: chenhao
 * @date: 2023-7-21 8:57
 */
@Service
@RequiredArgsConstructor
public class PushWhiteListServiceImpl implements IPushWhiteListService {
    private final TPushWhiteListRepository tPushWhiteListRepository;
    private final TPushTypeRepository tPushTypeRepository;
    private final DapPushConfig dapPushConfig;

    @Override
    public TPushWhiteList getByOaAccountAndTypeId(String oaAccount, Integer typeId) {
        return tPushWhiteListRepository.getPersonByOaAccountAndTypeId(oaAccount, typeId);
    }

    @Override
    public TPushWhiteList getByMobileAndTypeId(String mobile, Integer typeId) {
        return tPushWhiteListRepository.getPersonByMobileAndTypeId(mobile, typeId);
    }

    @Override
    public OaUser login(String oaUser, String typeCode) {
        OaUser user = new OaUser();
        user = JSON.parseObject(oaUser, OaUser.class);
        user.setRedirect(dapPushConfig.getDapErrorUrl());
        TPushType tPushTypeByTypeCode = tPushTypeRepository.getTPushTypeByTypeCode(typeCode);
        if (tPushTypeByTypeCode == null) {
            return user;
        }
        //增加权限过滤
        String dapIgnoreCode = dapPushConfig.getDapIgnoreCode();
        if(StringUtils.isNotBlank(dapIgnoreCode)){
            List<String> strings = Arrays.asList(dapIgnoreCode.split(";"));
            if (strings.contains(typeCode)){
                if(StringUtils.isNotBlank(user.getUser())){
                    user.setRedirect(String.format(dapPushConfig.getDapRedirectAccountUrl(),typeCode,user.getUser()));
                }else {
                    user.setRedirect(String.format(dapPushConfig.getDapRedirectPhoneUrl(),typeCode,user.getJobTel()));
                }
                return user;
            }
        }
        TPushWhiteList byOaAccountAndTypeId = getByOaAccountAndTypeId(user.getUser(), tPushTypeByTypeCode.getId());
        if (byOaAccountAndTypeId == null) {
            TPushWhiteList byMobileAndTypeId = getByMobileAndTypeId(user.getJobTel(), tPushTypeByTypeCode.getId());
            if(byMobileAndTypeId==null){
                return user;
            }else {
                user.setRedirect(String.format(dapPushConfig.getDapRedirectPhoneUrl(),typeCode,user.getJobTel()));
            }
        }else {
            user.setRedirect(String.format(dapPushConfig.getDapRedirectAccountUrl(),typeCode,user.getUser()));
        }
        return user;
    }
}

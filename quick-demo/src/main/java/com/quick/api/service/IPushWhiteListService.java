package com.quick.api.service;

import com.quick.api.repository.mapper.po.TPushWhiteList;
import com.quick.api.dto.OaUser;

/**
 * @description: 白名单服务
 * @author: chenhao
 * @date: 2023-7-21 8:54
 */
public interface IPushWhiteListService {

    /**
     *  oa账号和推送类型获取白名单信息
     * @param oaAccount
     * @param typeId
     * @return
     */
    TPushWhiteList getByOaAccountAndTypeId(String oaAccount, Integer typeId);

    /**
     *  手机号和推送类型获取白名单信息
     * @param mobile
     * @param typeId
     * @return
     */
    TPushWhiteList getByMobileAndTypeId(String mobile,Integer typeId);

    /**
     * 模拟oa登录
     * @param oaUser
     * @param typeCode
     * @return
     */
    OaUser login(String oaUser,String typeCode);
}

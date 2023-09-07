package com.quick.api.repository;

import com.quick.api.repository.mapper.po.TPushWhiteList;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 白名单表 服务类
 * </p>
 *
 * @author system
 * @since 2023-07-19
 */
public interface TPushWhiteListRepository extends IService<TPushWhiteList> {
    /**
     * 根据oaAccount
     * @param oaAccount
     * @param typeId
     * @return
     */
    TPushWhiteList getPersonByOaAccountAndTypeId(String oaAccount,Integer typeId);

    /**
     * 手机号和推送类型获取白名单信息
     * @param mobile
     * @param typeId
     * @return
     */
    TPushWhiteList getPersonByMobileAndTypeId(String mobile,Integer typeId);
}

package com.quick.api.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.quick.api.repository.TPushWhiteListRepository;
import com.quick.api.repository.mapper.TPushWhiteListMapper;
import com.quick.api.repository.mapper.po.TPushWhiteList;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 白名单表 服务实现类
 * </p>
 *
 * @author system
 * @since 2023-07-19
 */
@Service
public class TPushWhiteListRepositoryImpl extends ServiceImpl<TPushWhiteListMapper, TPushWhiteList> implements TPushWhiteListRepository {

    @Override
    public TPushWhiteList getPersonByOaAccountAndTypeId(String oaAccount, Integer typeId) {
        LambdaQueryWrapper<TPushWhiteList>  wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(TPushWhiteList::getTypeId,typeId)
                .eq(TPushWhiteList::getOaAcount,oaAccount)
                .eq(TPushWhiteList::getDeleted,Boolean.FALSE);
        return getOne(wrapper);
    }

    @Override
    public TPushWhiteList getPersonByMobileAndTypeId(String mobile, Integer typeId) {
        LambdaQueryWrapper<TPushWhiteList>  wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(TPushWhiteList::getTypeId,typeId)
                .eq(TPushWhiteList::getMobile,mobile)
                .eq(TPushWhiteList::getDeleted,Boolean.FALSE);
        return getOne(wrapper);
    }
}

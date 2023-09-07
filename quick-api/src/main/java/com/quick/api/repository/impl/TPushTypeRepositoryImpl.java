package com.quick.api.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.quick.api.repository.TPushTypeRepository;
import com.quick.api.repository.mapper.TPushTypeMapper;
import com.quick.api.repository.mapper.po.TPushType;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 推送种类表 服务实现类
 * </p>
 *
 * @author system
 * @since 2023-07-19
 */
@Service
public class TPushTypeRepositoryImpl extends ServiceImpl<TPushTypeMapper, TPushType> implements TPushTypeRepository {

    @Override
    public TPushType getTPushTypeByTypeCode(String typeCode) {
        LambdaQueryWrapper<TPushType> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(TPushType::getPushTypeCode,typeCode).eq(TPushType::getDeleted, Boolean.FALSE);
        return getOne(wrapper);
    }
}

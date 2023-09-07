package com.quick.api.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.quick.api.repository.TPushTypeTitleRepository;
import com.quick.api.repository.mapper.TPushTypeTitleMapper;
import com.quick.api.repository.mapper.po.TPushTypeTitle;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 不同推送种类的表头 服务实现类
 * </p>
 *
 * @author system
 * @since 2023-07-19
 */
@Service
public class TPushTypeTitleRepositoryImpl extends ServiceImpl<TPushTypeTitleMapper, TPushTypeTitle> implements TPushTypeTitleRepository {

    @Override
    public List<TPushTypeTitle> getTitleByTypeId(Integer typeId) {
        LambdaQueryWrapper<TPushTypeTitle> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(TPushTypeTitle::getTypeId,typeId).eq(TPushTypeTitle::getDeleted, Boolean.FALSE);
        return list(queryWrapper);
    }
}

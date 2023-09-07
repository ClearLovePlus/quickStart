package com.quick.api.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.quick.api.repository.TPushTypeParaRepository;
import com.quick.api.repository.mapper.TPushTypeParaMapper;
import com.quick.api.repository.mapper.po.TPushTypePara;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 查询条件配置表 服务实现类
 * </p>
 *
 * @author system
 * @since 2023-07-19
 */
@Service
public class TPushTypeParaRepositoryImpl extends ServiceImpl<TPushTypeParaMapper, TPushTypePara> implements TPushTypeParaRepository {

    @Override
    public List<TPushTypePara> getPushTypeParaByTypeId(Integer typeId) {
        LambdaQueryWrapper<TPushTypePara> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(TPushTypePara::getTypeId,typeId).eq(TPushTypePara::getDeleted, Boolean.FALSE);
        return list(queryWrapper);
    }
}

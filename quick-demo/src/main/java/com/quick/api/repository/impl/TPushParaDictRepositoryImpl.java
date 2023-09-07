package com.quick.api.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.quick.api.repository.TPushParaDictRepository;
import com.quick.api.repository.mapper.TPushParaDictMapper;
import com.quick.api.repository.mapper.po.TPushParaDict;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 查询条件下拉条件枚举表 服务实现类
 * </p>
 *
 * @author system
 * @since 2023-07-19
 */
@Service
public class TPushParaDictRepositoryImpl extends ServiceImpl<TPushParaDictMapper, TPushParaDict> implements TPushParaDictRepository {

    @Override
    public List<TPushParaDict> getPushParaDictByTypeId(Integer typeId,Integer paraId) {
        LambdaQueryWrapper<TPushParaDict> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(TPushParaDict::getTypeId,typeId);
        if(paraId!=null){
            queryWrapper.eq(TPushParaDict::getParaId,paraId);
        }
        queryWrapper.eq(TPushParaDict::getDeleted, Boolean.FALSE);
        return list(queryWrapper);
    }
}

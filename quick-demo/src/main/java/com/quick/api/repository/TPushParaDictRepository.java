package com.quick.api.repository;

import com.quick.api.repository.mapper.po.TPushParaDict;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 查询条件下拉条件枚举表 服务类
 * </p>
 *
 * @author system
 * @since 2023-07-19
 */
public interface TPushParaDictRepository extends IService<TPushParaDict> {
    /**
     * 通过typeId和paraId获取
     * @param typeId
     * @param paraId
     * @return
     */
    List<TPushParaDict> getPushParaDictByTypeId(Integer typeId,Integer paraId);

}

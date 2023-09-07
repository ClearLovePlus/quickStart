package com.quick.api.repository;

import com.quick.api.repository.mapper.po.TPushType;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 推送种类表 服务类
 * </p>
 *
 * @author system
 * @since 2023-07-19
 */
public interface TPushTypeRepository extends IService<TPushType> {
    /**
     * 根据配置的推送类型查询推送信息
     * @param typeCode
     * @return
     */
    TPushType getTPushTypeByTypeCode(String typeCode);
}

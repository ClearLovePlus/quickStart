package com.quick.api.repository;

import com.quick.api.repository.mapper.po.TPushTypePara;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 查询条件配置表 服务类
 * </p>
 *
 * @author system
 * @since 2023-07-19
 */
public interface TPushTypeParaRepository extends IService<TPushTypePara> {
    List<TPushTypePara> getPushTypeParaByTypeId(Integer typeId);

}

package com.quick.api.repository;

import com.quick.api.repository.mapper.po.TPushTypeTitle;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 不同推送种类的表头 服务类
 * </p>
 *
 * @author system
 * @since 2023-07-19
 */
public interface TPushTypeTitleRepository extends IService<TPushTypeTitle> {
    /**
     * 通过配置的推送类型id查询要展示的表头
     * @param typeId
     * @return
     */
    List<TPushTypeTitle> getTitleByTypeId(Integer typeId);

}

package com.quick.api.service;

import com.topsperity.common.dto.Resp;
import com.quick.api.dto.ParaDictResponseDTO;

import java.util.List;

/**
 * @description: 获取下拉列表枚举
 * @author: chenhao
 * @date: 2023-7-20 14:44
 */
public interface IPushTypeParaDictService {
    /**
     * 通过推送分类和参数id获取下拉列表的值
     * @param typeId
     * @param paraId
     * @return
     */
    Resp<List<ParaDictResponseDTO>> getDictByTypeIdAndParaId(Integer typeId,Integer paraId);
}

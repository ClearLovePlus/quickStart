package com.quick.api.service;

import com.quick.api.dto.DiffTypeDetailResponseDTO;
import com.summer.common.dto.Resp;

import java.util.List;

/**
 * @description: 调用魔星平台接口的调用类
 * @author: chenhao
 * @date: 2023-7-20 13:21
 */
public interface IMagicService {
    /**
     * 获取详情对比详情
     * @param url
     * @return
     */
    Resp<List<DiffTypeDetailResponseDTO>> getDiffTypeDetail(String url);
}

package com.quick.api.service;

import com.quick.api.repository.mapper.po.TPushType;
import com.topsperity.common.dto.Resp;
import com.quick.api.dto.DiffTypeDetailResponseDTO;
import com.quick.api.dto.PushContentResponseDTO;

/**
 * @description:
 * @author: chenhao
 * @date: 2023-7-20 8:50
 */
public interface IPushTypeService {
     /**
      * 获取请求报文头
      * @param typeCode
      * @param dicValue
      * @return
      */
     Resp<PushContentResponseDTO<DiffTypeDetailResponseDTO>> getDiffTypeDetailResponse(String  typeCode, String dicValue);

     TPushType getByPushCode(String pushCode);

}

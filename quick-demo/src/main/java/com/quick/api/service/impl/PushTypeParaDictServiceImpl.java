package com.quick.api.service.impl;

import com.quick.api.repository.TPushParaDictRepository;
import com.quick.api.repository.mapper.po.TPushParaDict;
import com.quick.api.common.ReturnCodeEnum;
import com.quick.api.dto.ParaDictResponseDTO;
import com.quick.api.service.IPushTypeParaDictService;
import com.summer.common.dto.Resp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: chenhao
 * @date: 2023-7-20 14:47
 */
@Service
@RequiredArgsConstructor
public class PushTypeParaDictServiceImpl implements IPushTypeParaDictService {
    private final TPushParaDictRepository pushParaDictRepository;
    @Override
    public Resp<List<ParaDictResponseDTO>> getDictByTypeIdAndParaId(Integer typeId, Integer paraId) {
        if(typeId==null){
            return Resp.failed(ReturnCodeEnum.PARAMETER_ERROR.getCode(),ReturnCodeEnum.PARAMETER_ERROR.getMsg());
        }
        List<TPushParaDict> pushParaDictByTypeId = pushParaDictRepository.getPushParaDictByTypeId(typeId, paraId);
        if(pushParaDictByTypeId.isEmpty()){
            return Resp.ok();
        }
        List<ParaDictResponseDTO> collect = pushParaDictByTypeId.stream().map(p -> ParaDictResponseDTO.builder()
                .dicGroup(p.getDictGroup())
                .dicKey(p.getDictKey())
                .dicValue(p.getDictValue())
                .build()).collect(Collectors.toList());
        return Resp.ok(collect);
    }
}

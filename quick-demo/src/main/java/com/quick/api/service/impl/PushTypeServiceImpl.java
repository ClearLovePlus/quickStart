package com.quick.api.service.impl;

import com.quick.api.config.MagicConfig;
import com.quick.api.dto.*;
import com.quick.api.repository.TPushParaDictRepository;
import com.quick.api.repository.TPushTypeParaRepository;
import com.quick.api.repository.TPushTypeRepository;
import com.quick.api.repository.TPushTypeTitleRepository;
import com.quick.api.repository.mapper.po.TPushParaDict;
import com.quick.api.repository.mapper.po.TPushType;
import com.quick.api.repository.mapper.po.TPushTypePara;
import com.quick.api.repository.mapper.po.TPushTypeTitle;
import com.summer.common.constants.DapConstants;
import com.summer.common.dto.Resp;
import com.quick.api.common.ReturnCodeEnum;
import com.quick.api.service.IMagicService;
import com.quick.api.service.IPushTypeService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @description: 推送主类的所有操作放在这里
 * @author: chenhao
 * @date: 2023-7-20 9:53
 */
@Service
@RequiredArgsConstructor
public class PushTypeServiceImpl implements IPushTypeService {
    private final TPushTypeRepository tPushTypeRepository;
    private final TPushTypeParaRepository tPushTypeParaRepository;
    private final TPushTypeTitleRepository tPushTypeTitleRepository;
    private final TPushParaDictRepository tPushParaDictRepository;
    private final IMagicService magicService;
    private final MagicConfig magicConfig;


    @Override
    public Resp<PushContentResponseDTO<DiffTypeDetailResponseDTO>> getDiffTypeDetailResponse(String typeCode, String dicValue) {
        PushContentResponseDTO<DiffTypeDetailResponseDTO> content = new PushContentResponseDTO<>();
        if (StringUtils.isBlank(typeCode)) {
            return Resp.failed(ReturnCodeEnum.PARAMETER_ERROR.getCode(), ReturnCodeEnum.PARAMETER_ERROR.getMsg());
        }
        TPushType tPushTypeByTypeCode = tPushTypeRepository.getTPushTypeByTypeCode(typeCode);
        if (Objects.isNull(tPushTypeByTypeCode)) {
            return Resp.failed(ReturnCodeEnum.PUSH_TYPE_ERROR.getCode(), ReturnCodeEnum.PUSH_TYPE_ERROR.getMsg());
        }
        //获取该类型的title
        List<TPushTypeTitle> titles = tPushTypeTitleRepository.getTitleByTypeId(tPushTypeByTypeCode.getId());
        if (titles.isEmpty()) {
            return Resp.failed(ReturnCodeEnum.PUSH_TITLE_ERROR.getCode(), ReturnCodeEnum.PUSH_TITLE_ERROR.getMsg());
        }
        List<PushTitleResponseDTO> titleList = new ArrayList<>(titles.size());
        for (int i = 0; i < titles.size(); i++) {
            if (i == 0) {
                //固定第一列
                titleList.add(PushTitleResponseDTO.builder().titleKey(titles.get(i).getTitleKey()).titleName(titles.get(i).getTitleName())
                        .leftStyle("left").width(100).build());
            } else {
                titleList.add(PushTitleResponseDTO.builder().titleKey(titles.get(i).getTitleKey()).titleName(titles.get(i).getTitleName())
                        .width(150).build());
            }
        }
        content.setTitles(titleList);
        //构造下拉列表数据
        List<TPushParaDict> pushParaDictByTypeId = tPushParaDictRepository.getPushParaDictByTypeId(tPushTypeByTypeCode.getId(), null);
        Map<Integer, List<ParaDictResponseDTO>> dictMap = new HashMap<>();
        if (!pushParaDictByTypeId.isEmpty()) {
            Map<Integer, List<TPushParaDict>> maps = pushParaDictByTypeId.stream().collect(Collectors.groupingBy(TPushParaDict::getParaId));
            //转换
            maps.forEach((k, v) -> {
                dictMap.put(k, v.stream().map(p -> ParaDictResponseDTO.builder().dicGroup(p.getDictGroup())
                        .text(p.getDictKey()).value(p.getDictValue()).build()).collect(Collectors.toList()));
            });
        }
        //获取查询参数
        List<TPushTypePara> parameters = tPushTypeParaRepository.getPushTypeParaByTypeId(tPushTypeByTypeCode.getId());
        if (!parameters.isEmpty()) {
            List<PushParaResponseDTO> para = parameters.stream()
                    .map(p -> PushParaResponseDTO.builder().
                            paraKey(p.getParaKey()).
                            paraName(p.getParaName()).
                            paraType(p.getParaType()).id(p.getId())
                            .dropDownValue((dictMap.get(p.getId()))).build()).collect(Collectors.toList());
            content.setParameters(para);
        }
        String pushTypeUrl = tPushTypeByTypeCode.getPushTypeUrl();
        String url = magicConfig.getMagicUrl() + pushTypeUrl + dicValue;
        Resp<List<DiffTypeDetailResponseDTO>> diffTypeDetail = magicService.getDiffTypeDetail(url);
        if (diffTypeDetail != null && diffTypeDetail.getCode().equals(DapConstants.HTTP_SUCCESS)) {
            content.setDataList(diffTypeDetail.getData());
            content.setCount(diffTypeDetail.getData().size());
        } else {
            content.setCount(0);
        }
        return Resp.ok(content);
    }

    @Override
    public TPushType getByPushCode(String pushCode) {
        return tPushTypeRepository.getTPushTypeByTypeCode(pushCode);

    }
}

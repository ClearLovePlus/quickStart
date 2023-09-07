package com.quick.api.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * @description: 对比类别返回体
 * @author: chenhao
 * @date: 2023-7-20 13:34
 */
@ApiModel(value = "PushContentResponseDTO",description = "对比类别返回体")
@Data
public class PushContentResponseDTO<T> {
    private Integer count;
    private List<PushTitleResponseDTO> titles;
    private List<PushParaResponseDTO> parameters;
    private List<T> dataList;
}


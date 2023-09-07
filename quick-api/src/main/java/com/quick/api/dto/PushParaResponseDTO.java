package com.quick.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: chenhao
 * @date: 2023-7-20 16:54
 */
@Data
@ApiModel(value = "PushParaResponseDTO")
@Builder
public class PushParaResponseDTO {
    @ApiModelProperty(value = "查询参数key")
    private String paraKey;
    @ApiModelProperty(value = "查询参数姓名")
    private String paraName;
    @ApiModelProperty(value = "查询参数类型 1:输入类型 2:下拉类型")
    private Integer paraType;
    @ApiModelProperty(value = "查询参数id")
    private Integer id;
    @ApiModelProperty(value = "当paraType=2的时候返回改参数的下拉列表的值")
    private List<ParaDictResponseDTO> dropDownValue;
}

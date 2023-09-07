package com.quick.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 数据类型详情数据
 * @author: chenhao
 * @date: 2023-7-20 13:40
 */
@Data
@ApiModel(value = "DiffTypeDetailResponseDTO",description = "数据对比不同详情")
public class DiffTypeDetailResponseDTO {
    @ApiModelProperty("对比类型")
    private String diffType;
    @ApiModelProperty("证券代码")
    private String secuCode;
    @ApiModelProperty("证券名称")
    private String secuAbbr;
    @ApiModelProperty("聚源数据")
    private String jy;
    @ApiModelProperty("万得数据")
    private String  wind;

}

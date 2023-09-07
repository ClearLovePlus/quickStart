package com.quick.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @description:
 * @author: chenhao
 * @date: 2023-7-20 14:41
 */
@Data
@ApiModel(value = "ParaDictResponseDTO")
@Builder
public class ParaDictResponseDTO {
    @ApiModelProperty("下拉key")
    private String dicKey;
    @ApiModelProperty("下拉value")
    private String dicValue;
    @ApiModelProperty("分组")
    private String dicGroup;
    @ApiModelProperty("下拉key")
    private String text;
    @ApiModelProperty("下拉value")
    private String value;
}

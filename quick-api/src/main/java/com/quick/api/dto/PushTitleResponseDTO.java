package com.quick.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @description:
 * @author: chenhao
 * @date: 2023-7-20 16:51
 */
@Data
@ApiModel(value = "PushTitleResponseDTO")
@Builder
public class PushTitleResponseDTO {
    @ApiModelProperty("表头key")
    private String titleKey;
    @ApiModelProperty("表头名称")
    private String titleName;
    @ApiModelProperty("固定左边表头")
    private String leftStyle;
    @ApiModelProperty("数据表的宽度")
    private Integer width;
}

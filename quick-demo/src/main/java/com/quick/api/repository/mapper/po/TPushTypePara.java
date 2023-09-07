package com.quick.api.repository.mapper.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 查询条件配置表
 * </p>
 *
 * @author system
 * @since 2023-07-19
 */
@Getter
@Setter
@TableName("t_push_type_para")
@ApiModel(value = "TPushTypePara对象", description = "查询条件配置表")
public class TPushTypePara implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("t_push_type的主键id")
    private Integer typeId;

    @ApiModelProperty("表头key")
    private String paraKey;

    @ApiModelProperty("表头名称")
    private String paraName;

    @ApiModelProperty("0:输入参数 1:下拉参数")
    private Integer paraType;

    @ApiModelProperty("删除标志")
    private Boolean deleted;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("最后更新时间")
    private LocalDateTime updateTime;

    public static final String ID = "id";

    public static final String TYPE_ID = "type_id";

    public static final String PARA_KEY = "para_key";

    public static final String PARA_NAME = "para_name";

    public static final String PARA_TYPE = "para_type";

    public static final String DELETED = "deleted";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";
}

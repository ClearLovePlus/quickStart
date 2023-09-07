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
 * 不同推送种类的表头
 * </p>
 *
 * @author system
 * @since 2023-07-19
 */
@Getter
@Setter
@TableName("t_push_type_title")
@ApiModel(value = "TPushTypeTitle对象", description = "不同推送种类的表头")
public class TPushTypeTitle implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("t_push_type的主键id")
    private Integer typeId;

    @ApiModelProperty("表头key")
    private String titleKey;

    @ApiModelProperty("表头名称")
    private String titleName;

    @ApiModelProperty("删除标志")
    private Boolean deleted;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("最后更新时间")
    private LocalDateTime updateTime;

    public static final String ID = "id";

    public static final String TYPE_ID = "type_id";

    public static final String TITLE_KEY = "title_key";

    public static final String TITLE_NAME = "title_name";

    public static final String DELETED = "deleted";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";
}

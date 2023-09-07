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
 * 查询条件下拉条件枚举表
 * </p>
 *
 * @author system
 * @since 2023-07-19
 */
@Getter
@Setter
  @TableName("t_push_para_dict")
@ApiModel(value = "TPushParaDict对象", description = "查询条件下拉条件枚举表")
public class TPushParaDict implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("t_push_type的主键id")
      private Integer typeId;

      @ApiModelProperty("t_push_type_para主键id")
      private Integer paraId;

      @ApiModelProperty("下拉枚举分组")
      private String dictGroup;

      @ApiModelProperty("下拉枚举名")
      private String dictKey;

      @ApiModelProperty("下拉枚举值")
      private String dictValue;

      @ApiModelProperty("删除标志")
      private Boolean deleted;

      @ApiModelProperty("创建时间")
      private LocalDateTime createTime;

      @ApiModelProperty("最后更新时间")
      private LocalDateTime updateTime;

    public static final String ID = "id";

    public static final String TYPE_ID = "type_id";

    public static final String PARA_ID = "para_id";

    public static final String DICT_GROUP = "dict_group";

    public static final String DICT_KEY = "dict_key";

    public static final String DICT_VALUE = "dict_value";

    public static final String DELETED = "deleted";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";
  }

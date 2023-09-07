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
 * 推送种类表
 * </p>
 *
 * @author system
 * @since 2023-07-19
 */
@Getter
@Setter
  @TableName("t_push_type")
@ApiModel(value = "TPushType对象", description = "推送种类表")
public class TPushType implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("推送标识")
      private String pushTypeCode;

      @ApiModelProperty("推送名称")
      private String pushTypeName;

      @ApiModelProperty("调用magic_api的访问路径")
      private String pushTypeUrl;

      @ApiModelProperty("删除标志")
      private Boolean deleted;

      @ApiModelProperty("创建时间")
      private LocalDateTime createTime;

      @ApiModelProperty("最后更新时间")
      private LocalDateTime updateTime;

    public static final String ID = "id";

    public static final String PUSH_TYPE_CODE = "push_type_code";

    public static final String PUSH_TYPE_NAME = "push_type_name";

    public static final String PUSH_TYPE_URL = "push_type_url";

    public static final String DELETED = "deleted";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";
  }

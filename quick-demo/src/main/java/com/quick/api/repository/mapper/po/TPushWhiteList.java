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
 * 白名单表
 * </p>
 *
 * @author system
 * @since 2023-07-19
 */
@Getter
@Setter
  @TableName("t_push_white_list")
@ApiModel(value = "TPushWhiteList对象", description = "白名单表")
public class TPushWhiteList implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("t_push_type的主键id")
      private Integer typeId;

      @ApiModelProperty("手机号")
      private String mobile;

      @ApiModelProperty("oa账号")
      private String oaAcount;

      @ApiModelProperty("删除标志")
      private Boolean deleted;

      @ApiModelProperty("创建时间")
      private LocalDateTime createTime;

      @ApiModelProperty("最后更新时间")
      private LocalDateTime updateTime;

    public static final String ID = "id";

    public static final String TYPE_ID = "type_id";

    public static final String MOBILE = "mobile";

    public static final String OA_ACOUNT = "oa_acount";

    public static final String DELETED = "deleted";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";
  }

package io.github.pnoker.center.auth.entity.model;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.github.pnoker.common.constant.enums.EnableFlagEnum;
import io.github.pnoker.common.entity.ext.JsonExt;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 租户表
 * </p>
 *
 * @author pnoker
 * @since 2022.1.0
 */
@Getter
@Setter
@TableName("dc3_tenant")
public class TenantDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 租户名称
     */
    @TableField("tenant_name")
    private String tenantName;

    /**
     * 租户编号
     */
    @TableField("tenant_code")
    private String tenantCode;

    /**
     * 租户拓展信息
     */
    @TableField(value = "tenant_ext", typeHandler = JacksonTypeHandler.class)
    private JsonExt tenantExt;

    /**
     * 使能标识
     */
    @TableField("enable_flag")
    private EnableFlagEnum enableFlag;

    /**
     * 描述
     */
    @TableField("remark")
    private String remark;

    /**
     * 创建者ID
     */
    @TableField("creator_id")
    private Long creatorId;

    /**
     * 创建者名称
     */
    @TableField("creator_name")
    private String creatorName;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 操作者ID
     */
    @TableField("operator_id")
    private Long operatorId;

    /**
     * 操作者名称
     */
    @TableField("operator_name")
    private String operatorName;

    /**
     * 操作时间
     */
    @TableField("operate_time")
    private LocalDateTime operateTime;

    /**
     * 逻辑删除标识,0:未删除,1:已删除
     */
    @TableLogic
    @TableField("deleted")
    private Byte deleted;
}
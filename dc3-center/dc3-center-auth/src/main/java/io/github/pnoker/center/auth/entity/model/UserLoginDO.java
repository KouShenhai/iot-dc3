package io.github.pnoker.center.auth.entity.model;

import com.baomidou.mybatisplus.annotation.*;
import io.github.pnoker.common.constant.enums.EnableFlagEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户登录表
 * </p>
 *
 * @author pnoker
 * @since 2022.1.0
 */
@Getter
@Setter
@TableName("dc3_user_login")
public class UserLoginDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 登录名称，加密存储
     */
    @TableField("login_name")
    private String loginName;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 用户密码ID
     */
    @TableField("user_password_id")
    private Long userPasswordId;

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
/*
 * Copyright 2016-present the IoT DC3 original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.pnoker.center.manager.entity.query;

import io.github.pnoker.common.constant.enums.EnableFlagEnum;
import io.github.pnoker.common.entity.common.Pages;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * Device Query
 *
 * @author pnoker
 * @since 2022.1.0
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Schema(title = "DeviceQuery", description = "设备-查询")
public class DeviceQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "分页")
    private Pages page;

    private Long profileId;

    /**
     * 设备名称
     */
    @Schema(description = "设备名称")
    private String deviceName;

    /**
     * 设备编号
     */
    @Schema(description = "设备编号")
    private String deviceCode;

    /**
     * 驱动ID
     */
    @Schema(description = "驱动ID")
    private Long driverId;

    /**
     * 分组ID
     */
    @Schema(description = "分组ID")
    private Long groupId;

    /**
     * 使能标识
     */
    @Schema(description = "使能标识")
    private EnableFlagEnum enableFlag;

    /**
     * 租户ID
     */
    @Schema(description = "租户ID")
    private Long tenantId;
}
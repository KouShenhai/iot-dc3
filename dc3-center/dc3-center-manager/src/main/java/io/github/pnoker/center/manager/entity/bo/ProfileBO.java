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

package io.github.pnoker.center.manager.entity.bo;

import io.github.pnoker.common.entity.base.BaseBO;
import io.github.pnoker.common.entity.ext.ProfileExt;
import io.github.pnoker.common.enums.EnableFlagEnum;
import io.github.pnoker.common.enums.ProfileShareFlagEnum;
import io.github.pnoker.common.enums.ProfileTypeFlagEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Profile BO
 *
 * @author pnoker
 * @since 2022.1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileBO extends BaseBO {

    /**
     * 模板名称
     */
    private String profileName;

    /**
     * 模板编号
     */
    private String profileCode;

    /**
     * 模板共享类型标识
     */
    private ProfileShareFlagEnum profileShareFlag;

    /**
     * 模板类型标识
     */
    private ProfileTypeFlagEnum profileTypeFlag;

    /**
     * 分组ID
     */
    private Long groupId;

    /**
     * 模板拓展信息
     */
    private ProfileExt profileExt;

    /**
     * 使能标识
     */
    private EnableFlagEnum enableFlag;

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 签名
     */
    private String signature;

    /**
     * 版本
     */
    private Integer version;
}

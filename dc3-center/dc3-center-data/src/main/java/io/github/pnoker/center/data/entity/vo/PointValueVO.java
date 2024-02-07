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

package io.github.pnoker.center.data.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.pnoker.common.constant.common.TimeConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * PointValue VO
 *
 * @author pnoker
 * @since 2022.1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "PointValueRead", description = "位号值")
public class PointValueVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 设备ID
     */
    private Long deviceId;

    /**
     * 位号ID
     */
    private Long pointId;

    /**
     * 原始值
     */
    private String rawValue;

    /**
     * 处理值
     */
    private String value;

    private List<String> history;

    /**
     * 原始时间
     */
    @JsonFormat(pattern = TimeConstant.COMPLETE_DATE_FORMAT, timezone = TimeConstant.DEFAULT_TIMEZONE)
    private LocalDateTime originTime;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = TimeConstant.COMPLETE_DATE_FORMAT, timezone = TimeConstant.DEFAULT_TIMEZONE)
    private LocalDateTime createTime;
}
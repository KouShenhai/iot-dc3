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

package io.github.pnoker.center.data.biz.impl;

import cn.hutool.core.util.ObjectUtil;
import io.github.pnoker.center.data.biz.DeviceEventService;
import io.github.pnoker.common.constant.common.PrefixConstant;
import io.github.pnoker.common.entity.dto.DeviceEventDTO;
import io.github.pnoker.common.redis.service.RedisService;
import io.github.pnoker.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * DeviceService Impl
 *
 * @author pnoker
 * @since 2022.1.0
 */
@Slf4j
@Service
public class DeviceEventServiceImpl implements DeviceEventService {

    @Resource
    private RedisService redisService;


    @Override
    public void heartbeatEvent(DeviceEventDTO entityDTO) {
        DeviceEventDTO.DeviceStatus deviceStatus = JsonUtil.parseObject(entityDTO.getContent(), DeviceEventDTO.DeviceStatus.class);
        if (ObjectUtil.isNull(deviceStatus)) {
            return;
        }
        redisService.setKey(PrefixConstant.DEVICE_STATUS_KEY_PREFIX + deviceStatus.getDeviceId(), deviceStatus.getStatus(), deviceStatus.getTimeOut(), deviceStatus.getTimeUnit());
    }

}

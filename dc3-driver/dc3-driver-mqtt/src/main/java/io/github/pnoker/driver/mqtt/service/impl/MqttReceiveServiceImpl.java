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

package io.github.pnoker.driver.mqtt.service.impl;

import io.github.pnoker.common.driver.service.DriverSenderService;
import io.github.pnoker.common.entity.MqttMessage;
import io.github.pnoker.common.entity.dto.PointValueDTO;
import io.github.pnoker.common.mqtt.service.MqttReceiveService;
import io.github.pnoker.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author pnoker
 * @since 2022.1.0
 */
@Slf4j
@Service
public class MqttReceiveServiceImpl implements MqttReceiveService {

    @Resource
    private DriverSenderService driverSenderService;

    @Override
    public void receiveValue(MqttMessage mqttMessage) {
        // do something to process your mqtt messages
        log.info(JsonUtil.toPrettyJsonString(mqttMessage));
        PointValueDTO pointValue = JsonUtil.parseObject(mqttMessage.getPayload(), PointValueDTO.class);
        pointValue.setOriginTime(LocalDateTime.now());
        driverSenderService.pointValueSender(pointValue);
    }

    @Override
    public void receiveValues(List<MqttMessage> mqttMessageList) {
        // do something to process your mqtt messages
        log.info(JsonUtil.toPrettyJsonString(mqttMessageList));
        List<PointValueDTO> pointValues = mqttMessageList.stream()
                .map(mqttMessage -> {
                    PointValueDTO pointValue = JsonUtil.parseObject(mqttMessage.getPayload(), PointValueDTO.class);
                    pointValue.setOriginTime(LocalDateTime.now());
                    return pointValue;
                }).collect(Collectors.toList());
        driverSenderService.pointValueSender(pointValues);
    }
}

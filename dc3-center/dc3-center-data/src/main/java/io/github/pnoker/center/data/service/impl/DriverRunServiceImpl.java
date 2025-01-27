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

package io.github.pnoker.center.data.service.impl;

import io.github.pnoker.center.data.entity.model.DriverRunDO;
import io.github.pnoker.center.data.mapper.DriverRunMapper;
import io.github.pnoker.center.data.service.DriverRunService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DriverRunServiceImpl implements DriverRunService {
    @Resource
    private DriverRunMapper driverRunMapper;

    @Override
    public List<DriverRunDO> get7daysDuration(Long driverId, String code) {
        return driverRunMapper.get7daysDuration(driverId, code);
    }
}

/*
 * Copyright 2016-present the original author or authors.
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

package io.github.pnoker.center.manager.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.pnoker.center.manager.entity.query.PointAttributeConfigPageQuery;
import io.github.pnoker.center.manager.service.NotifyService;
import io.github.pnoker.center.manager.service.PointInfoService;
import io.github.pnoker.common.constant.service.ManagerServiceConstant;
import io.github.pnoker.common.entity.R;
import io.github.pnoker.common.enums.MetadataCommandTypeEnum;
import io.github.pnoker.common.model.PointAttributeConfig;
import io.github.pnoker.common.valid.Insert;
import io.github.pnoker.common.valid.Update;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 位号配置信息 Controller
 *
 * @author pnoker
 * @since 2022.1.0
 */
@Slf4j
@RestController
@RequestMapping(ManagerServiceConstant.POINT_INFO_URL_PREFIX)
public class PointInfoController {

    @Resource
    private PointInfoService pointInfoService;

    @Resource
    private NotifyService notifyService;

    /**
     * 新增 PointInfo
     *
     * @param pointAttributeConfig PointInfo
     * @return PointInfo
     */
    @PostMapping("/add")
    public R<PointAttributeConfig> add(@Validated(Insert.class) @RequestBody PointAttributeConfig pointAttributeConfig) {
        try {
            PointAttributeConfig add = pointInfoService.add(pointAttributeConfig);
            if (ObjectUtil.isNotNull(add)) {
                notifyService.notifyDriverPointInfo(MetadataCommandTypeEnum.ADD, add);
                return R.ok(add);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

    /**
     * 根据 ID 删除 PointInfo
     *
     * @param id 位号信息ID
     * @return 是否删除
     */
    @PostMapping("/delete/{id}")
    public R<Boolean> delete(@NotNull @PathVariable(value = "id") String id) {
        try {
            PointAttributeConfig pointAttributeConfig = pointInfoService.selectById(id);
            if (ObjectUtil.isNotNull(pointAttributeConfig) && pointInfoService.delete(id)) {
                notifyService.notifyDriverPointInfo(MetadataCommandTypeEnum.DELETE, pointAttributeConfig);
                return R.ok();
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

    /**
     * 修改 PointInfo
     *
     * @param pointAttributeConfig PointInfo
     * @return PointInfo
     */
    @PostMapping("/update")
    public R<PointAttributeConfig> update(@Validated(Update.class) @RequestBody PointAttributeConfig pointAttributeConfig) {
        try {
            PointAttributeConfig update = pointInfoService.update(pointAttributeConfig);
            if (ObjectUtil.isNotNull(update)) {
                notifyService.notifyDriverPointInfo(MetadataCommandTypeEnum.UPDATE, update);
                return R.ok(update);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

    /**
     * 根据 ID 查询 PointInfo
     *
     * @param id 位号信息ID
     * @return PointInfo
     */
    @GetMapping("/id/{id}")
    public R<PointAttributeConfig> selectById(@NotNull @PathVariable(value = "id") String id) {
        try {
            PointAttributeConfig select = pointInfoService.selectById(id);
            if (ObjectUtil.isNotNull(select)) {
                return R.ok(select);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

    /**
     * 根据 属性ID、设备ID 和 位号ID 查询 PointInfo
     *
     * @param attributeId Attribute ID
     * @param deviceId    设备ID
     * @param pointId     Point ID
     * @return PointInfo
     */
    @GetMapping("/attribute_id/{attributeId}/device_id/{deviceId}/point_id/{pointId}")
    public R<PointAttributeConfig> selectByAttributeIdAndDeviceIdAndPointId(@NotNull @PathVariable(value = "attributeId") String attributeId,
                                                                            @NotNull @PathVariable(value = "deviceId") String deviceId,
                                                                            @NotNull @PathVariable(value = "pointId") String pointId) {
        try {
            PointAttributeConfig select = pointInfoService.selectByAttributeIdAndDeviceIdAndPointId(attributeId, deviceId, pointId);
            if (ObjectUtil.isNotNull(select)) {
                return R.ok(select);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

    /**
     * 根据 设备ID 和 位号ID 查询 PointInfo
     *
     * @param deviceId 设备ID
     * @param pointId  位号ID
     * @return PointInfo
     */
    @GetMapping("/device_id/{deviceId}/point_id/{pointId}")
    public R<List<PointAttributeConfig>> selectByDeviceIdAndPointId(@NotNull @PathVariable(value = "deviceId") String deviceId,
                                                                    @NotNull @PathVariable(value = "pointId") String pointId) {
        try {
            List<PointAttributeConfig> select = pointInfoService.selectByDeviceIdAndPointId(deviceId, pointId);
            if (ObjectUtil.isNotNull(select)) {
                return R.ok(select);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

    /**
     * 根据 设备ID 查询 PointInfo
     *
     * @param deviceId 设备ID
     * @return PointInfo
     */
    @GetMapping("/device_id/{deviceId}")
    public R<List<PointAttributeConfig>> selectByDeviceId(@NotNull @PathVariable(value = "deviceId") String deviceId) {
        try {
            List<PointAttributeConfig> select = pointInfoService.selectByDeviceId(deviceId);
            if (ObjectUtil.isNotNull(select)) {
                return R.ok(select);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

    /**
     * 模糊分页查询 PointInfo
     *
     * @param pointInfoPageQuery PointInfo Dto
     * @return Page Of PointInfo
     */
    @PostMapping("/list")
    public R<Page<PointAttributeConfig>> list(@RequestBody(required = false) PointAttributeConfigPageQuery pointInfoPageQuery) {
        try {
            if (ObjectUtil.isEmpty(pointInfoPageQuery)) {
                pointInfoPageQuery = new PointAttributeConfigPageQuery();
            }
            Page<PointAttributeConfig> page = pointInfoService.list(pointInfoPageQuery);
            if (ObjectUtil.isNotNull(page)) {
                return R.ok(page);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

}

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

package io.github.pnoker.center.manager.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.pnoker.center.manager.dal.LabelBindManager;
import io.github.pnoker.center.manager.entity.builder.LabelBindForManagerBuilder;
import io.github.pnoker.center.manager.entity.model.LabelBindDO;
import io.github.pnoker.center.manager.entity.query.LabelBindQuery;
import io.github.pnoker.center.manager.service.LabelBindService;
import io.github.pnoker.common.constant.common.QueryWrapperConstant;
import io.github.pnoker.common.entity.bo.LabelBindBO;
import io.github.pnoker.common.entity.common.Pages;
import io.github.pnoker.common.exception.*;
import io.github.pnoker.common.utils.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * LabelBindService Impl
 *
 * @author pnoker
 * @since 2022.1.0
 */
@Slf4j
@Service
public class LabelBindServiceImpl implements LabelBindService {

    @Resource
    private LabelBindForManagerBuilder labelBindForManagerBuilder;

    @Resource
    private LabelBindManager labelBindManager;

    @Override
    public void save(LabelBindBO entityBO) {
        checkDuplicate(entityBO, false, true);

        LabelBindDO entityDO = labelBindForManagerBuilder.buildDOByBO(entityBO);
        if (!labelBindManager.save(entityDO)) {
            throw new AddException("The label bind add failed");
        }
    }

    @Override
    public void remove(Long id) {
        getDOById(id, true);

        if (!labelBindManager.removeById(id)) {
            throw new DeleteException("The label bind delete failed");
        }
    }

    @Override
    public void update(LabelBindBO entityBO) {
        getDOById(entityBO.getId(), true);

        checkDuplicate(entityBO, true, true);

        LabelBindDO entityDO = labelBindForManagerBuilder.buildDOByBO(entityBO);
        entityDO.setOperateTime(null);
        if (!labelBindManager.updateById(entityDO)) {
            throw new UpdateException("The label bind update failed");
        }
    }

    @Override
    public LabelBindBO selectById(Long id) {
        LabelBindDO entityDO = getDOById(id, false);
        return labelBindForManagerBuilder.buildBOByDO(entityDO);
    }

    @Override
    public Page<LabelBindBO> selectByPage(LabelBindQuery entityQuery) {
        if (ObjectUtil.isNull(entityQuery.getPage())) {
            entityQuery.setPage(new Pages());
        }
        Page<LabelBindDO> entityPageDO = labelBindManager.page(PageUtil.page(entityQuery.getPage()), fuzzyQuery(entityQuery));
        return labelBindForManagerBuilder.buildBOPageByDOPage(entityPageDO);
    }

    /**
     * 构造模糊查询
     *
     * @param entityQuery {@link LabelBindQuery}
     * @return {@link LambdaQueryWrapper}
     */
    private LambdaQueryWrapper<LabelBindDO> fuzzyQuery(LabelBindQuery entityQuery) {
        LambdaQueryWrapper<LabelBindDO> wrapper = Wrappers.<LabelBindDO>query().lambda();
        wrapper.eq(ObjectUtil.isNotEmpty(entityQuery.getLabelId()), LabelBindDO::getLabelId, entityQuery.getLabelId());
        wrapper.eq(ObjectUtil.isNotEmpty(entityQuery.getEntityId()), LabelBindDO::getEntityId, entityQuery.getEntityId());
        wrapper.eq(LabelBindDO::getTenantId, entityQuery.getTenantId());
        return wrapper;
    }

    /**
     * 重复性校验
     *
     * @param entityBO       {@link LabelBindBO}
     * @param isUpdate       是否为更新操作
     * @param throwException 如果重复是否抛异常
     * @return 是否重复
     */
    private boolean checkDuplicate(LabelBindBO entityBO, boolean isUpdate, boolean throwException) {
        LambdaQueryWrapper<LabelBindDO> wrapper = Wrappers.<LabelBindDO>query().lambda();
        wrapper.eq(LabelBindDO::getEntityTypeFlag, entityBO.getEntityTypeFlag());
        wrapper.eq(LabelBindDO::getLabelId, entityBO.getLabelId());
        wrapper.eq(LabelBindDO::getEntityId, entityBO.getEntityId());
        wrapper.eq(LabelBindDO::getTenantId, entityBO.getTenantId());
        wrapper.last(QueryWrapperConstant.LIMIT_ONE);
        LabelBindDO one = labelBindManager.getOne(wrapper);
        if (ObjectUtil.isNull(one)) {
            return false;
        }
        boolean duplicate = !isUpdate || !one.getId().equals(entityBO.getId());
        if (throwException && duplicate) {
            throw new DuplicateException("标签绑定重复");
        }
        return duplicate;
    }

    /**
     * 根据 主键ID 获取
     *
     * @param id             ID
     * @param throwException 是否抛异常
     * @return {@link LabelBindDO}
     */
    private LabelBindDO getDOById(Long id, boolean throwException) {
        LabelBindDO entityDO = labelBindManager.getById(id);
        if (throwException && ObjectUtil.isNull(entityDO)) {
            throw new NotFoundException("标签绑定不存在");
        }
        return entityDO;
    }

}

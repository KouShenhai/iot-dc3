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

package io.github.pnoker.center.auth.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.pnoker.center.auth.entity.bo.UserPasswordBO;
import io.github.pnoker.center.auth.entity.query.UserPasswordQuery;
import io.github.pnoker.center.auth.mapper.UserPasswordMapper;
import io.github.pnoker.center.auth.service.UserPasswordService;
import io.github.pnoker.common.constant.common.AlgorithmConstant;
import io.github.pnoker.common.entity.common.Pages;
import io.github.pnoker.common.exception.AddException;
import io.github.pnoker.common.exception.DeleteException;
import io.github.pnoker.common.exception.NotFoundException;
import io.github.pnoker.common.exception.UpdateException;
import io.github.pnoker.common.utils.DecodeUtil;
import io.github.pnoker.common.utils.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 用户密码服务接口实现类
 *
 * @author pnoker
 * @since 2022.1.0
 */
@Slf4j
@Service
public class UserPasswordServiceImpl implements UserPasswordService {

    @Resource
    private UserPasswordMapper userPasswordMapper;

    @Override
    @Transactional
    public void save(UserPasswordBO entityBO) {
        entityBO.setLoginPassword(DecodeUtil.md5(entityBO.getLoginPassword()));
        // 插入 userPassword 数据，并返回插入后的 userPassword
        if (userPasswordMapper.insert(entityBO) < 1) {
            throw new AddException("The user password add failed: {}", entityBO.toString());
        }
    }

    @Override
    @Transactional
    public void remove(Long id) {
        UserPasswordBO userPasswordBO = selectById(id);
        if (ObjectUtil.isNull(userPasswordBO)) {
            throw new NotFoundException("The user password does not exist");
        }

        if (userPasswordMapper.deleteById(id) < 1) {
            throw new DeleteException("The user password delete failed");
        }
    }

    @Override
    public void update(UserPasswordBO entityBO) {
        UserPasswordBO selectById = selectById(entityBO.getId());
        if (ObjectUtil.isNull(selectById)) {
            throw new NotFoundException();
        }
        entityBO.setLoginPassword(DecodeUtil.md5(entityBO.getLoginPassword()));
        entityBO.setOperateTime(null);
        if (userPasswordMapper.updateById(entityBO) < 1) {
            throw new UpdateException("The user password update failed");
        }
    }

    @Override
    public UserPasswordBO selectById(Long id) {
        return null;
    }

    @Override
    public Page<UserPasswordBO> selectByPage(UserPasswordQuery entityQuery) {
        if (ObjectUtil.isNull(entityQuery.getPage())) {
            entityQuery.setPage(new Pages());
        }
        return userPasswordMapper.selectPage(PageUtil.page(entityQuery.getPage()), fuzzyQuery(entityQuery));
    }

    @Override
    public void restPassword(Long id) {
        UserPasswordBO userPasswordBO = selectById(id);
        if (ObjectUtil.isNotNull(userPasswordBO)) {
            userPasswordBO.setLoginPassword(DecodeUtil.md5(AlgorithmConstant.DEFAULT_PASSWORD));
            update(userPasswordBO);
        }
    }

    private LambdaQueryWrapper<UserPasswordBO> fuzzyQuery(UserPasswordQuery query) {
        return Wrappers.<UserPasswordBO>query().lambda();
    }

}

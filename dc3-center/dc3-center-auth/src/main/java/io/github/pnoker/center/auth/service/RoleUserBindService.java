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

package io.github.pnoker.center.auth.service;

import io.github.pnoker.center.auth.entity.query.RoleUserBindPageQuery;
import io.github.pnoker.common.base.Service;
import io.github.pnoker.common.model.Role;
import io.github.pnoker.common.model.RoleUserBind;

import java.util.List;

/**
 * role user mapper service
 *
 * @author linys
 * @since 2023.04.02
 */
public interface RoleUserBindService extends Service<RoleUserBind, RoleUserBindPageQuery> {

    /**
     * 根据 租户id 和 用户id 查询
     *
     * @param tenantId 租户id
     * @param userId   用户id
     * @return Role list
     */
    List<Role> listRoleByTenantIdAndUserId(String tenantId, String userId);
}

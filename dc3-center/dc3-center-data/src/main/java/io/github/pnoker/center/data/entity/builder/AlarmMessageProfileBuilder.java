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

package io.github.pnoker.center.data.entity.builder;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.pnoker.center.data.entity.bo.AlarmMessageProfileBO;
import io.github.pnoker.center.data.entity.model.AlarmMessageProfileDO;
import io.github.pnoker.center.data.entity.vo.AlarmMessageProfileVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * AlarmMessageProfile Builder
 *
 * @author pnoker
 * @since 2022.1.0
 */
@Mapper(componentModel = "spring")
public interface AlarmMessageProfileBuilder {

    /**
     * VO to BO
     *
     * @param entityVO EntityVO
     * @return EntityBO
     */
    AlarmMessageProfileBO buildBOByVO(AlarmMessageProfileVO entityVO);

    /**
     * VOList to BOList
     *
     * @param entityVOList EntityVO Array
     * @return EntityBO Array
     */
    List<AlarmMessageProfileBO> buildBOListByVOList(List<AlarmMessageProfileVO> entityVOList);

    /**
     * BO to DO
     *
     * @param entityBO EntityBO
     * @return EntityDO
     */
    @Mapping(target = "alarmMessageExt", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    AlarmMessageProfileDO buildDOByBO(AlarmMessageProfileBO entityBO);

    /**
     * BOList to DOList
     *
     * @param entityBOList EntityBO Array
     * @return EntityDO Array
     */
    List<AlarmMessageProfileDO> buildDOListByBOList(List<AlarmMessageProfileBO> entityBOList);

    /**
     * DO to BO
     *
     * @param entityDO EntityDO
     * @return EntityBO
     */
    AlarmMessageProfileBO buildBOByDO(AlarmMessageProfileDO entityDO);

    /**
     * DOList to BOList
     *
     * @param entityDOList EntityDO Array
     * @return EntityBO Array
     */
    List<AlarmMessageProfileBO> buildBOListByDOList(List<AlarmMessageProfileDO> entityDOList);

    /**
     * BO to VO
     *
     * @param entityBO EntityBO
     * @return EntityVO
     */
    AlarmMessageProfileVO buildVOByBO(AlarmMessageProfileBO entityBO);

    /**
     * BOList to VOList
     *
     * @param entityBOList EntityBO Array
     * @return EntityVO Array
     */
    List<AlarmMessageProfileVO> buildVOListByBOList(List<AlarmMessageProfileBO> entityBOList);

    /**
     * DOPage to BOPage
     *
     * @param entityPageDO EntityDO Page
     * @return EntityBO Page
     */
    @Mapping(target = "optimizeJoinOfCountSql", ignore = true)
    Page<AlarmMessageProfileBO> buildBOPageByDOPage(Page<AlarmMessageProfileDO> entityPageDO);

    /**
     * BOPage to VOPage
     *
     * @param entityPageBO EntityBO Page
     * @return EntityVO Page
     */
    @Mapping(target = "optimizeJoinOfCountSql", ignore = true)
    Page<AlarmMessageProfileVO> buildVOPageByBOPage(Page<AlarmMessageProfileBO> entityPageBO);
}
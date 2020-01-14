package com.wisdompoint.platform.service;

import com.wisdompoint.platform.model.Event;

/**
 * @author： ShaoJie
 * @data： 2019年12月26日 15:31
 * @Description： 违规事件 service
 */
public interface EventService {

    /**
     * 删除违规事件信息
     *
     * @param id 违规事件id
     */
    void deleteEvent(String id);

    /**
     * 根据违规代码查询违规信息
     *
     * @param code 违规代码
     * @return
     */
    Event getContextByCode(String code);

    /**
     * 根据 违规代码 code 查询违规事件
     * @param code 违规代码
     * @return 违规事件
     */
    Event findAllByCode(String code);
}

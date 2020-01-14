package com.wisdompoint.platform.dao;

import com.wisdompoint.platform.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author： ShaoJie
 * @data： 2019年12月26日 15:30
 * @Description： 违规事件接口
 */
public interface EventRepository extends JpaRepository<Event, String>, JpaSpecificationExecutor<Event> {

    /**
     * 删除违规事件信息
     *
     * @param status 修改的状态
     * @param id     违规事件id
     */
    @Modifying
    @Query(value = "UPDATE event SET status = :status where id = :id", nativeQuery = true)
    void deleteEvent(Integer status, String id);

    /**
     * 根据 违规代码 code 查询违规事件
     * @param code 违规代码
     * @return 违规事件
     */
    Event findAllByCode(String code);

}

package com.wisdompoint.platform.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.github.wenhao.jpa.Specifications;
import com.wisdompoint.platform.dao.EventRepository;
import com.wisdompoint.platform.model.Event;
import com.wisdompoint.platform.service.EventService;
import com.wisdompoint.platform.util.em.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author： ShaoJie
 * @data： 2019年12月26日 15:31
 * @Description： 违规事件服务
 */
@Service
@Transactional
public class EventServiceImpl implements EventService {

    /**
     * event 接口
     */
    @Autowired
    private EventRepository eventRepository;

    /**
     * 查询所有的违规事件
     *
     * @return
     */
    public Page<Event> listEvents(Pageable pageable) {
        Example<Event> eventExample = Example.of(new Event().setStatus(StatusEnum.NORMAL.getId()));
        return eventRepository.findAll(eventExample, pageable);
    }

    /**
     * 根据违规等级查询违规信息
     *
     * @param level 违规等级
     * @return
     */
    public List<Event> listContextByLevel(String level) {
        Example<Event> eventExample = Example.of(new Event().setLevel(level).setStatus(StatusEnum.NORMAL.getId()));
        return eventRepository.findAll(eventExample);
    }

    /**
     * 根据违规编号查询违规信息
     *
     * @param id 违规事件编号
     * @return
     */
    public Event listEventById(String id) {
        return eventRepository.findById(id).get();
    }

    /**
     * 插入 event 数据
     *
     * @param event 违规事件对象
     */
    public void insertEvent(Event event) {
        event.setId(IdUtil.simpleUUID()).setCreateTime(DateUtil.date()).setStatus(StatusEnum.NORMAL.getId());
        eventRepository.save(event);
    }

    /**
     * 修改 event 数据
     *
     * @param event 违规事件对象
     */
    public String updateEvent(Event event) {
        if (event == null) {
            return "请填写必要参数";
        }
        eventRepository.save(event);
        return "success";
    }

    /**
     * 删除 event 对象
     *
     * @param id 违规事件id
     * @return
     */
    public void deleteEvent(String id) {
        eventRepository.deleteEvent(StatusEnum.DELETE.getId(), id);
    }

    /**
     * 根据 违规代码 code 查询违规事件
     * @param code 违规代码
     * @return 违规事件
     */
    public Event findAllByCode(String code){
        return eventRepository.findAllByCode(code);
    }

    /**
     * 根据违规代码查询违规内容
     * findAll() 查询的是一个集合 获取其中第一个信息 .get(Integer index)
     *
     * @param code 违规代码
     * @return
     */
    @Override
    public Event getContextByCode(String code) {
        Example<Event> eventExample = Example.of(new Event()
                .setStatus(StatusEnum.NORMAL.getId())
                .setCode(code));
        return eventRepository.findOne(eventExample).get();
    }

    /**
     * 搜索违规事件信息
     *
     * @param pageable 分页信息
     * @param context  违规事件名称
     * @return
     */
    public Page<Event> searchEvent(Pageable pageable, String context) {
        Specification<Event> specification = Specifications.<Event>and()
                .like("context", "%" + context + "%")
                .eq("status", StatusEnum.NORMAL.getId())
                .build();
        return eventRepository.findAll(specification, pageable);
    }
}

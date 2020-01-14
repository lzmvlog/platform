package com.wisdompoint.platform.controller;

import cn.hutool.core.util.StrUtil;
import com.wisdompoint.platform.model.Event;
import com.wisdompoint.platform.service.impl.EventServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author： ShaoJie
 * @data： 2019年12月26日 15:33
 * @Description： 违规事件 控制器
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "event")
@Slf4j
@Api(value = "EventController", tags = "违规事件")
public class EventController {

    /**
     * 违规事件的服务
     */
    @Autowired
    private EventServiceImpl eventService;

    /**
     * 查询违规事件
     *
     * @return
     */
    @GetMapping("listEvents")
    @ApiOperation("查询违规事件")
    public Page<Event> listEvents(@RequestParam Integer pageNum,
                                  @RequestParam Integer pageSize) {
        log.info("{ 查询违规事件 第{}页}", pageNum + 1);
        // 注意： 这里的 pageNum 是从 0 开始的
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, "createTime"));
        return eventService.listEvents(pageable);
    }

    /**
     * 根据违规等级查询违规信息
     *
     * @param level 违规等级
     * @return
     */
    @GetMapping("listContextByLevel")
    @ApiOperation("根据违规等级查询违规信息")
    public List<Event> listContextByLevel(@RequestParam String level) {
        log.info("根据违规等级查询违规信息 违规等级：{}", level);
        return eventService.listContextByLevel(level);
    }

    /**
     * 根据违规代码查询违规信息
     * 前端获取时需要确定准确的下标 这里一定是第一个 也就是下标为 0
     * 当然也可以这样去处理 eventService.getContextByCode(code).get(0).getContext();
     *
     * @param code 违规代码
     * @return
     */
    @GetMapping("getContextByCode")
    public Event getContextByCode(@RequestParam String code) {
        log.info("{ 根据违规代码查询违规信息 违规代码：{} }", code);
        return eventService.getContextByCode(code);
    }

    /**
     * 根据违规编号查询违规信息
     *
     * @param id 违规事件编号
     * @return
     */
    @GetMapping("listEventById")
    @ApiOperation("根据违规编号查询违规信息息")
    public Event listEventById(String id) {
        log.info("{ 根据违规编号查询违规信息 违规编号：{}}", id);
        return eventService.listEventById(id);
    }

    /**
     * 新增违规事件
     *
     * @param event event 对象
     * @return
     */
    @PutMapping("insertEvent")
    @ApiOperation("新增违规事件")
    public String insertEvent(@RequestBody Event event) {
        if (event == null) {
            log.info("{ 缺少参数 }");
            return "请填写必要参数";
        }
        log.info("{ 新增违规事件 }");
        eventService.insertEvent(event);
        return "success";
    }

    /**
     * 修改违规事件
     *
     * @param event event 对象
     * @return
     */
    @PostMapping("updateEvent")
    @ApiOperation("修改违规事件")
    public String updateEvent(@RequestBody Event event) {
        return eventService.updateEvent(event);
    }

    /**
     * 删除违规事件
     *
     * @param id 违规事件的id
     * @return
     */
    @DeleteMapping("deleteEvent")
    @ApiOperation("删除违规事件")
    public String deleteEvent(@RequestParam String id) {
        if (StrUtil.hasBlank(id)) {
            return "id is null";
        }
        log.info("{ 删除违规事件 事件 编号：{}}", id);
        eventService.deleteEvent(id);
        return "success";
    }

    /**
     * 搜索违规事件信息
     *
     * @param pageNum  页码
     * @param pageSize 每页显示参数
     * @param context  违规事件信息名称
     * @return
     */
    @ApiOperation("根据违规信息查询")
    @GetMapping("searchEvent")
    public Page<Event> searchEmp(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam String context) {
        log.info("{ 根据违规信息查询  信息：{}}", context);
        // 注意： 这里的 pageNum 是从 0 开始的
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, "createTime"));
        return eventService.searchEvent(pageable, context);
    }

}

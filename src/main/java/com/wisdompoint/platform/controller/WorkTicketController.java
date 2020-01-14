package com.wisdompoint.platform.controller;

import cn.hutool.core.date.DateUtil;
import com.wisdompoint.platform.model.WorkTicket;
import com.wisdompoint.platform.service.impl.WorkTicketServiceImpl;
import com.wisdompoint.platform.util.em.StatusEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author： ShaoJie
 * @data： 2020年01月02日 11:08
 * @Description： 工作票控制器 controller
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "camera")
@Slf4j
@Api(value = "WorkTicketController", tags = "工作票控制器")
public class WorkTicketController {

    /**
     * 工作票服务
     */
    @Autowired
    private WorkTicketServiceImpl ticketService;

    /**
     * 新增工作票信息
     *
     * @param workTicket 工作票信息
     * @return
     */
    @ApiOperation("")
    @PutMapping("insertWorkTicket")
    public String insertWorkTicket(WorkTicket workTicket) {
        workTicket.setStatus(StatusEnum.NORMAL.getId())
                .setCreateTime(DateUtil.date());
        // TODO 这里需要查询一写登录人信息 确定工作票 创建人信息 尽量在登录时将信息保存在 session 中 可能是更好的方法
        ticketService.insertWorkTicket(workTicket);
        return "success";
    }

}

package com.wisdompoint.platform.controller;

import com.wisdompoint.platform.service.impl.ViolationLogServiceImpl;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author： ShaoJie
 * @data： 2020年01月17日 11:57
 * @Description： 违规日志控制器
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("ViolationLog")
@Api(value = "违规日志controller", tags = "违规记录日志")
@Slf4j
public class ViolationLogController {

    @Autowired
    private ViolationLogServiceImpl violationLogService;

   /* *//**
     * 保存违规日志
     *
     * @param violationLog 违规实体
     * @return
     *//*
    @ApiOperation(value = "处理违规记录")
    @PutMapping(value = "insertViolationLog")
    public void insertViolationLog(@RequestBody ViolationLog violationLog) {
        violationLogService.insertViolationLog(violationLog);
    }*/

}

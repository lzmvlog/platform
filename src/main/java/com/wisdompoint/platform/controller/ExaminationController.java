package com.wisdompoint.platform.controller;

import com.wisdompoint.platform.service.impl.ExaminationServiceImpl;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author： ShaoJie
 * @data： 2019年12月20日 17:22
 * @Description： 考试纪律 控制器
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "examination")
@Slf4j
@Api(value = "ExaminationController", tags = "考试纪律")
public class ExaminationController {

    /**
     * 考试记录服务
     */
    @Autowired
    private ExaminationServiceImpl examinationService;


}

package com.wisdompoint.platform.service.impl;

import com.wisdompoint.platform.dao.ViolationLogRepository;
import com.wisdompoint.platform.model.ViolationLog;
import com.wisdompoint.platform.service.ViolationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author： ShaoJie
 * @data： 2020年01月17日 11:55
 * @Description： 违规记录日志 service
 */
@Service
@Transactional
public class ViolationLogServiceImpl implements ViolationLogService {

    @Autowired
    private ViolationLogRepository violationLogRepository;

    /**
     * 保存处理日志
     *
     * @param violationLog 日志实体
     */
    @Override
    public void insertViolationLog(ViolationLog violationLog) {
        violationLogRepository.save(violationLog);
    }
}

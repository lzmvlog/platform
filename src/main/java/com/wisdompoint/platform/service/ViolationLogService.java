package com.wisdompoint.platform.service;

import com.wisdompoint.platform.model.ViolationLog;

/**
 * @author： ShaoJie
 * @data： 2020年01月17日 11:55
 * @Description：
 */
public interface ViolationLogService {


    /**
     * 保存处理日志
     *
     * @param violationLog 日志实体
     */
    void insertViolationLog(ViolationLog violationLog);

}

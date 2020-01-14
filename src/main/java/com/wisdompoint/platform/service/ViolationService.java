package com.wisdompoint.platform.service;

import com.wisdompoint.platform.model.dto.ViolationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author： ShaoJie
 * @data： 2019年12月18日 11:49
 * @Description： 违规的服务
 */
public interface ViolationService {

    /**
     * 处理违规记录
     *
     * @param id      违规的 ID 编号
     */
    void processViolationsInfo(String id);

    /**
     * 查询指定的字段
     *
     * @param pageable 分页信息
     * @return
     */
    Page<ViolationDto> findAllByStatus(Pageable pageable);
}

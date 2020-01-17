package com.wisdompoint.platform.service;

import com.wisdompoint.platform.model.Violation;
import com.wisdompoint.platform.model.dto.ViolationDto;
import com.wisdompoint.platform.util.em.ProcessEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author： ShaoJie
 * @data： 2019年12月18日 11:49
 * @Description： 违规的服务
 */
public interface ViolationService {

    /**
     * 审核违规记录
     *
     * @param id      违规的 ID 编号
     * @param process 处理的步骤
     * @see ProcessEnum
     */
    void processViolationsInfo(Integer process, String id);

    /**
     * 查询指定的字段
     *
     * @param pageable 分页信息
     * @return
     */
    Page<ViolationDto> findAllByStatus(Pageable pageable);

    /**
     * 删除违规记录
     *
     * @param id 违规id
     */
    void deleteViolationLog(String id);

    /**
     * 处理违规记录
     *
     * @param id 违规的 ID 编号
     */
    Violation findViolationProcessStatus(String id);

    /**
     * 查询用户的违规信息
     *
     * @param memberId 用户的id
     * @param pageable 分页
     * @return
     */
    Page<ViolationDto> findAllByMemberId(String memberId, Pageable pageable);
}

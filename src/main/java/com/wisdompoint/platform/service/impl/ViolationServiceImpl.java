package com.wisdompoint.platform.service.impl;

import com.github.wenhao.jpa.Specifications;
import com.wisdompoint.platform.dao.ViolationRepository;
import com.wisdompoint.platform.model.Violation;
import com.wisdompoint.platform.model.dto.ViolationDto;
import com.wisdompoint.platform.service.ViolationService;
import com.wisdompoint.platform.util.em.ProcessEnum;
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
 * @data： 2019年12月18日 11:49
 * @Description： 违规服务接口
 */
@Service
@Transactional
public class ViolationServiceImpl implements ViolationService {

    /**
     * 违规接口管理
     */
    @Autowired
    private ViolationRepository violationRepository;

    /**
     * 接收 ai 传递的数据
     *
     * @param violation
     * @return
     */
    public Violation insertViolation(Violation violation) {
        return violationRepository.save(violation);
    }

    /**
     * 处理违规记录
     *
     * @param id      违规的 ID 编号
     * @param process 处理的步骤
     * @see ProcessEnum
     */
    @Override
    public void processViolationsInfo(Integer process, String id) {
        // 执行处理违规记录
        violationRepository.processViolationsInfo(process, id);
    }

    /**
     * 查询指定的字段
     *
     * @param pageable 分页信息
     * @return
     */
    @Override
    public Page<ViolationDto> findAllByStatus(Pageable pageable) {
        return violationRepository.findAllByStatus(StatusEnum.NORMAL.getId(), pageable);
    }

    /**
     * 删除违规记录
     *
     * @param id 违规id
     */
    @Override
    public void deleteViolationLog(String id) {
        violationRepository.deleteViolation(StatusEnum.DELETE.getId(), id);
    }

    /**
     * 根据 违规编号 查询审核的状态
     *
     * @param id 违规的 ID 编号
     * @return
     */
    @Override
    public Violation findViolationProcessStatus(String id) {
        Example<Violation> example = Example.of(new Violation()
                .setStatus(StatusEnum.NORMAL.getId())
                .setId(id));
        return violationRepository.findOne(example).get();
    }

    /**
     * 查询用户的违规信息
     * 只有当处理过后 才能算是这次违规 审核和处理 应该都算是这个人的违规
     *
     * @param memberId 用户的id
     * @param pageable 分页
     * @return
     */
    @Override
    public Page<ViolationDto> findAllByMemberId(String memberId, Pageable pageable) {
        Specification<ViolationDto> specification = Specifications.<ViolationDto>and()
                .eq("memberId",memberId)
                .eq("status", StatusEnum.NORMAL.getId())
                .eq("process", ProcessEnum.PROCESSED.getId(),ProcessEnum.REVIEW.getId())
                .build();
        return violationRepository.findAll(specification, pageable);

    }

    /**
     * 查询违规图片
     *
     * @param id 违规的编号
     * @return
     */
    public Violation getViolationImg(String id) {
        Example<Violation> example = Example.of(new Violation()
                .setId(id)
                .setStatus(StatusEnum.NORMAL.getId()));
        return violationRepository.findOne(example).get();
    }

    /**
     * 根据员工的 id 查询 所有的违规纪律
     *
     * @param memberId 用户的id
     * @return
     */
    public List<Violation> getViolationLog(String memberId) {
        Example<Violation> example = Example.of(new Violation()
                .setId(memberId));
        return violationRepository.findAll(example);
    }

}

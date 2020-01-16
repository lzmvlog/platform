package com.wisdompoint.platform.service.impl;

import com.wisdompoint.platform.dao.ExaminationRepository;
import com.wisdompoint.platform.model.Examination;
import com.wisdompoint.platform.service.ExaminationService;
import com.wisdompoint.platform.util.em.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author： ShaoJie
 * @data： 2019年12月20日 16:40
 * @Description： 考试记录服务
 */
@Service
@Transactional
public class ExaminationServiceImpl implements ExaminationService {

    /**
     * Examination的接口
     */
    @Autowired
    private ExaminationRepository examinationRepository;

    /**
     * 新增考试记录
     *
     * @param examination
     * @return
     */
    public Examination insertExamination(Examination examination) {
        examination.setEligibility(false)
                .setStatus(StatusEnum.NORMAL.getId());
        return examinationRepository.save(examination);
    }

    /**
     * 查询考试记录
     *
     * @return
     */
    public Page<Examination> listExaminations(Pageable pageable) {
        Example<Examination> examinationExample = Example.of(new Examination()
                .setStatus(StatusEnum.NORMAL.getId()));
        return examinationRepository.findAll(examinationExample, pageable);
    }

    /**
     * 修改考试记录
     *
     * @param examination
     */
    public void updateExamination(Examination examination) {
        examinationRepository.save(examination);
    }

}

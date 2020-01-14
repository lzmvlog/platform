package com.wisdompoint.platform.service.impl;

import com.wisdompoint.platform.dao.WorkTicketRepository;
import com.wisdompoint.platform.model.WorkTicket;
import com.wisdompoint.platform.service.WorkTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author： ShaoJie
 * @data： 2020年01月02日 11:03
 * @Description： 工作票服务
 */
@Service
@Transactional
public class WorkTicketServiceImpl implements WorkTicketService {

    /**
     * 工作票的接口
     */
    @Autowired
    private WorkTicketRepository workTicketRepository;

    /**
     * 新增工作票
     *
     * @param workTicket 工作票对象
     */
    @Override
    public void insertWorkTicket(WorkTicket workTicket) {
        workTicketRepository.save(workTicket);
    }
}

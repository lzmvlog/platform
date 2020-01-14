package com.wisdompoint.platform.service;

import com.wisdompoint.platform.model.WorkTicket;

/**
 * @author： ShaoJie
 * @data： 2020年01月02日 11:03
 * @Description： 工作票 service
 */
public interface WorkTicketService {
    /**
     * 新增工作票
     *
     * @param workTicket 工作票对象
     */
    void insertWorkTicket(WorkTicket workTicket);
}

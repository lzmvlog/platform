package com.wisdompoint.platform.dao;

import com.wisdompoint.platform.model.WorkTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author： ShaoJie
 * @data： 2020年01月02日 11:00
 * @Description： 工作票接口
 */
public interface WorkTicketRepository extends JpaRepository<WorkTicket, String>, JpaSpecificationExecutor<WorkTicket> {

}

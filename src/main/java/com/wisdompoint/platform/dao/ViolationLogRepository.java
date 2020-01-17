package com.wisdompoint.platform.dao;

import com.wisdompoint.platform.model.ViolationLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author： ShaoJie
 * @data： 2020年01月17日 11:53
 * @Description： 违规记录日志
 */
public interface ViolationLogRepository extends JpaRepository<ViolationLog, String> {

}

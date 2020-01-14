package com.wisdompoint.platform.dao;

import com.wisdompoint.platform.model.Examination;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author： ShaoJie
 * @data： 2019年12月20日 16:27
 * @Description： 考试记录管理接口
 */
public interface ExaminationRepository extends JpaRepository<Examination,String> {

}

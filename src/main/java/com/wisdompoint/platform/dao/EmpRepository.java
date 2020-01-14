package com.wisdompoint.platform.dao;

import com.wisdompoint.platform.model.Emp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author： ShaoJie
 * @data： 2019年12月19日 11:00
 * @Description： 员工接口
 */
public interface EmpRepository extends JpaRepository<Emp, String>, JpaSpecificationExecutor<Emp> {

    /**
     * 删除员工的信息
     *
     * @param status 修改的状态
     * @param id     员工的id
     */
    @Modifying
    @Query(value = "UPDATE emp SET status = :status where id = :id", nativeQuery = true)
    void deleteEmp(Integer status, String id);

}

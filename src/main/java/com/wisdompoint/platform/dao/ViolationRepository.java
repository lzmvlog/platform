package com.wisdompoint.platform.dao;

import com.wisdompoint.platform.model.Violation;
import com.wisdompoint.platform.model.dto.ViolationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author： ShaoJie
 * @data： 2019年12月18日 11:47
 * @Description： 违规 Repository 层
 */
public interface ViolationRepository extends JpaRepository<Violation, String> {

    /**
     * 处理ViolationsInfo
     *
     * @param id      违规的 ID 编号
     * @param process 处理信息编号
     */
    @Modifying
    @Query(value = "UPDATE violation SET process = :process WHERE id = :id", nativeQuery = true)
    void processViolationsInfo(Integer process, String id);

    /**
     * 删除员工的信息
     *
     * @param status 修改的状态
     * @param id     员工的id
     */
    @Modifying
    @Query(value = "UPDATE violation SET status = :status where id = :id", nativeQuery = true)
    void deleteViolation(Integer status, String id);

    /**
     * 查询指定的字段 排除除了图片以外的所有字段
     *
     * @param status   数据状态
     * @param pageable 分页信息
     * @return
     * @see com.wisdompoint.platform.util.em.StatusEnum
     */
    Page<ViolationDto> findAllByStatus(Integer status, Pageable pageable);

    /**
     * 查询用户的违规信息
     *
     * @param spec     动态查询
     * @param pageable 分页
     * @return
     */
    Page<ViolationDto> findAll(Specification<ViolationDto> spec, Pageable pageable);
}

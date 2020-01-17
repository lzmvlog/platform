package com.wisdompoint.platform.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.github.wenhao.jpa.Specifications;
import com.wisdompoint.platform.dao.EmpRepository;
import com.wisdompoint.platform.model.Emp;
import com.wisdompoint.platform.service.EmpService;
import com.wisdompoint.platform.util.em.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author： ShaoJie
 * @data： 2019年12月19日 11:02
 * @Description： 员工服务
 */
@Service
@Transactional
public class EmpServiceImpl implements EmpService {

    /**
     * Emp的接口
     */
    @Autowired
    private EmpRepository empRepository;

    /**
     * 新增员工信息
     *
     * @param emp 员工信息参数
     * @return Emp
     */
    public Emp saveEmpInfo(Emp emp) {
        emp.setId(IdUtil.fastSimpleUUID());
        emp.setStatus(StatusEnum.NORMAL.getId());
        emp.setCreateTime(DateUtil.date());
        // 执行新增操作
        return empRepository.save(emp);
    }

    /**
     * 删除员工信息
     *
     * @param id 员工ID
     */
    public void deleteEmp(String id) {
        // 执行删除操作
        empRepository.deleteEmp(StatusEnum.DELETE.getId(), id);
    }

    /**
     * 查询员工信息
     *
     * @param id 员工ID
     * @return
     */
    public Emp getEmp(String id) {
        return empRepository.findById(id).get();
    }

    /**
     * 修改员工信息
     *
     * @param emp 员工信息参数
     */
    public void updateEmpInfo(Emp emp) {
        empRepository.save(emp);
    }

    /**
     * 查询员工信息
     *
     * @param pageable 分页信息
     * @return
     */
    public Page<Emp> queryEmpInfo(Pageable pageable) {
        Example<Emp> empExample = Example.of(new Emp().setStatus(StatusEnum.NORMAL.getId()));
        return empRepository.findAll(empExample, pageable);
    }

    /**
     * 搜索员工信息
     *
     * @param pageable 分页信息
     * @param name     员工信息
     * @return
     */
    public Page<Emp> searchEmp(Pageable pageable, String name) {
        Specification<Emp> specification = Specifications.<Emp>and()
                .like("name", "%" + name + "%")
                .eq("status",StatusEnum.NORMAL.getId())
                .build();
        return empRepository.findAll(specification, pageable);
    }

}

package com.wisdompoint.platform.controller;

import cn.hutool.core.util.StrUtil;
import com.wisdompoint.platform.model.Emp;
import com.wisdompoint.platform.service.impl.EmpServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

/**
 * @author： ShaoJie
 * @data： 2019年12月18日 13:37
 * @Description： Emp的控制器
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "emp")
@Slf4j
@Api(value = "EmpController", tags = "员工")
public class EmpController {

    /**
     * 员工 的 Service
     */
    @Autowired
    private EmpServiceImpl empService;

    /**
     * 新增员工信息
     *
     * @param emp 员工信息参数
     * @return
     */
    @ApiOperation("新增员工信息")
    @PutMapping("saveEmpInfo")
    public String saveEmpInfo(@RequestBody Emp emp) {
        if (emp.getName() == "") return "请填写必要参数";
        else {
            empService.saveEmpInfo(emp);
        }
        log.info("{ 新增员工信息 }");
        return "success";
    }

    /**
     * 删除员工信息
     *
     * @param id 员工 ID
     * @return
     */
    @ApiOperation("删除员工信息")
    @ApiImplicitParam(name = "id", value = "员工ID", dataType = "String", paramType = "query")
    @DeleteMapping("removeEmpInfo/{id}")
    public String removeEmpInfo(@PathVariable(value = "id") String id) {
        // 判断传入参数是否空指针
        if (StrUtil.hasBlank(id)) {
            return "id is null";
        }
        log.info("{ 删除员工信息 ： {} }", id);
        // 执行删除操作
        empService.deleteEmp(id);
        return "success";
    }

    /**
     * 修改员工信息
     *
     * @param emp 员工信息参数
     * @return
     */
    @ApiOperation("修改员工信息")
    @PostMapping("updateEmpInfo")
    public String updateEmpInfo(@RequestBody Emp emp) {
        empService.updateEmpInfo(emp);
        return "success";
    }

    /**
     * 查询员工信息
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation("查询员工信息")
    @GetMapping("queryEmpInfo/{pageNum}/{pageSize}")
    public Page<Emp> queryEmpInfo(@PathVariable(value = "pageNum") Integer pageNum, @PathVariable(value = "pageSize") Integer pageSize) {
        // 注意： 这里的 pageNum 是从 0 开始的
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, "createTime"));
        log.info("{ 查询员工信息 当前第{}页}", pageNum + 1);
        // 执行查询操作
        return empService.queryEmpInfo(pageable);
    }

    /**
     * 查询员工信息
     *
     * @param id
     * @return
     */
    @ApiOperation("根据员工的id查询员工信息")
    @GetMapping("getEmpInfo")
    public Emp getEmp(@RequestParam String id) {
        log.info("{ 查询单个员工信息 ： {}}", id);
        return empService.getEmp(id);
    }

    /**
     * 搜索员工信息
     *
     * @param pageNum  页码
     * @param pageSize 每页显示参数
     * @param name     员工姓名 模糊查询
     * @return
     */
    @ApiOperation("根据员工的姓名查询员工信息")
    @GetMapping("searchEmp")
    public Page<Emp> searchEmp(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam String name) {
        log.info("{根据员工姓名查询员工信息  信息：{}}", name);
        // 注意： 这里的 pageNum 是从 0 开始的
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, "createTime"));
        return empService.searchEmp(pageable, name);
    }

}

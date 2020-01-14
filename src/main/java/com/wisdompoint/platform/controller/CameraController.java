package com.wisdompoint.platform.controller;

import com.wisdompoint.platform.model.Camera;
import com.wisdompoint.platform.service.impl.CameraServiceImpl;
import io.swagger.annotations.Api;
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
 * @data： 2019年12月31日 11:02
 * @Description： 摄像头 控制器
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "camera")
@Slf4j
@Api(value = "CameraController", tags = "摄像头控制器")
public class CameraController {

    /**
     * 摄像头的服务
     */
    @Autowired
    private CameraServiceImpl cameraService;

    /**
     * 查询摄像头信息
     *
     * @param pageNum  页数
     * @param pageSize 每页显示的数据条数
     * @return
     */
    @ApiOperation("查询摄像头信息")
    @GetMapping("queryCameraInfo")
    public Page<Camera> listCameras(@PathVariable(value = "pageNum") Integer pageNum, @PathVariable(value = "pageSize") Integer pageSize) {
        // 注意： 这里的 pageNum 是从 0 开始的
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, "createTime"));
        log.info("{ 查询摄像头信息 当前第{}页}", pageNum + 1);
        return cameraService.listCameras(pageable);
    }

    /**
     * 保存摄像头
     *
     * @return
     */
    @ApiOperation("保存多个摄像头")
    @PutMapping("insertCamera")
    public String insertCamera(@RequestBody Camera camera) {
        cameraService.insertCamera(camera);
        return "success";
    }

}

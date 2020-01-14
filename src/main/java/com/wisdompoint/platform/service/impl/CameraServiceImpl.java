package com.wisdompoint.platform.service.impl;

import com.wisdompoint.platform.dao.CameraRepository;
import com.wisdompoint.platform.model.Camera;
import com.wisdompoint.platform.util.em.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author： ShaoJie
 * @data： 2019年12月31日 10:55
 * @Description： 摄像头的服务
 */
@Service
@Transactional
public class CameraServiceImpl {

    /**
     * 摄像头的接口
     */
    @Autowired
    private CameraRepository cameraRepository;

    /**
     * 查询摄像头信息
     *
     * @param pageable 分页信息
     * @return
     */
    public Page<Camera> listCameras(Pageable pageable) {
        Example<Camera> cameraExample = Example.of(new Camera().setStatus(StatusEnum.NORMAL.getId()));
        return cameraRepository.findAll(cameraExample, pageable);
    }


    /**
     * 插入摄像头的信息
     *
     * @param cameras 摄像头的对象
     */
    public void insertCamera(Camera cameras) {
        cameras.setStatus(StatusEnum.NORMAL.getId());
        cameraRepository.save(cameras);
    }

    /**
     * 修改摄像头的信息
     *
     * @param cameras 摄像头的对象
     */
    public void UpdateCamera(Camera cameras) {
        cameraRepository.save(cameras);
    }

    /**
     * 删除摄像头的信息
     * <p>
     * save 方法要求 如果需要调用此方法 就需要传递 这个对象的所有的属性
     * 可以调用这个方法来进行删除 但是效率貌似不高 现在整个系统只有这一个调用 save 方法删除的方法
     *
     * @param cameras 摄像头的对象
     */
    public void deleteCamera(Camera cameras) {
        cameras.setStatus(StatusEnum.DELETE.getId());
        cameraRepository.save(cameras);
    }

}

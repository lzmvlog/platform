package com.wisdompoint.platform.dao;

import com.wisdompoint.platform.model.Camera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author： ShaoJie
 * @data： 2019年12月31日 10:53
 * @Description： 摄像头的信息
 */
public interface CameraRepository extends JpaRepository<Camera, String>, JpaSpecificationExecutor<Camera> {
}

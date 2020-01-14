package com.wisdompoint.platform.dao;

import com.wisdompoint.platform.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author： ShaoJie
 * @data： 2019年12月18日 13:33
 * @Description： 菜单的接口
 */
public interface MenuRepository extends JpaRepository<Menu, String> {

}

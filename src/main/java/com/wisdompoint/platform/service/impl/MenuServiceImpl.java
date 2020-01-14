package com.wisdompoint.platform.service.impl;

import cn.hutool.core.util.IdUtil;
import com.wisdompoint.platform.dao.MenuRepository;
import com.wisdompoint.platform.model.Menu;
import com.wisdompoint.platform.service.MenuService;
import com.wisdompoint.platform.util.em.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author： ShaoJie
 * @data： 2019年12月18日 13:35
 * @Description： 菜单服务
 */
@Service
@Transactional
public class MenuServiceImpl implements MenuService {

    /**
     * 菜单接口管理
     */
    @Autowired
    MenuRepository menuRepository;

    /**
     * 获取菜单的集合
     *
     * @return
     */
    public List<Menu> listMenus() {
        return menuRepository.findAll();
    }

    /**
     * 插入菜单
     *
     * @param menu
     * @return
     */
    public Menu insertMenu(Menu menu) {
        menu.setVisible(true);
        menu.setId(IdUtil.simpleUUID());
        menu.setStatus(StatusEnum.NORMAL.getId());
        return menuRepository.save(menu);
    }

}

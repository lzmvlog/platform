package com.wisdompoint.platform.controller;

import com.wisdompoint.platform.model.Menu;
import com.wisdompoint.platform.service.impl.MenuServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author： ShaoJie
 * @data： 2019年12月18日 13:37
 * @Description： 菜单的控制器
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "menu")
@Slf4j
@Api(value = "菜单controller", tags = "菜单")
public class MenuController {

    /**
     * 菜单服务
     */
    @Autowired
    private MenuServiceImpl menuService;

    /**
     * 查询菜单
     *
     * @return
     */
    @ApiOperation(value = "查询菜单")
    @GetMapping(value = "listMenus")
    public List<Map<String, Object>> listMenus() {
        log.info("{ 获取菜单项 }");
        // 获取到的所有菜单项
        List<Menu> menusList = menuService.listMenus();
        // 返回的数据
        List<Map<String, Object>> menus = new ArrayList<>();
        for (Menu menu : menusList) {
            // 自定义的 map 保存主菜单
            Map<String, Object> menusMap = new HashMap<>();
            // 将子节点添加到父节点上 这个时候 需要一个 childMap 来保存这个字目录
            List<Map<String, Object>> child = new ArrayList<>();
            // 如果不存在父节点 那一定是主菜单
            if (menu.getParentId().equals("")) {
                menusMap.put("name", menu.getName());
                menusMap.put("icon", menu.getIcon());
                for (Menu childs : menusList) {
                    // 自定义的 map 存放子节点
                    Map<String, Object> childMap = new HashMap<>();
                    // 拿到 父节点的 id 和 每一个子节点 的父节点 id 去比较
                    if (menu.getId().equals(childs.getParentId())) {
                        childMap.put("name", menu.getName());
                        childMap.put("icon", menu.getIcon());
                        child.add(childMap);
                    }
                }
                menusMap.put("child", child);
                menus.add(menusMap);
            }
        }
        return menus;
    }

    /**
     * 插入菜单
     *
     * @param menu
     * @return
     */
    @ApiOperation(value = "插入菜单")
    @PutMapping(value = "insertMenu")
    public Menu insertMenu(@RequestBody Menu menu) {
        log.info("{ 新增菜单 }");
        return menuService.insertMenu(menu);
    }

}

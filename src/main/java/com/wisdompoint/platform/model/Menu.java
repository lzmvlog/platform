package com.wisdompoint.platform.model;

import com.wisdompoint.platform.util.em.StatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * @author： ShaoJie
 * @data： 2019年12月18日 10:43
 * @Description： 菜单
 */
@Data
@Entity
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = "menu")
@ApiModel(value = "menu", description = "菜单")
public class Menu {

    /**
     * 菜单id
     */
    @Id
    @Column(name = "id")
    @ApiModelProperty(name = "id", dataType = "String", value = "菜单id")
    private String id;

    /**
     * 菜单的名称
     */
    @Column(name = "name")
    @ApiModelProperty(name = "name", dataType = "String", value = "菜单的名称")
    private String name;

    /**
     * 菜单的上一级
     */
    @Column(name = "parent_id")
    @ApiModelProperty(name = "parent_id", dataType = "String", value = "菜单的上一级")
    private String parentId;

    /**
     * 菜单的访问路径
     */
    @Column(name = "web_url")
    @ApiModelProperty(name = "web_url", dataType = "String", value = "菜单的访问路径")
    private String webUrl;

    /**
     * 菜单的
     */
    @Column(name = "order_index")
    @ApiModelProperty(name = "order_index", dataType = "Integer", value = "菜单的")
    private Integer orderIndex;

    /**
     * 菜单是否可见
     */
    @Column(name = "visible", columnDefinition = "bit(1) default 1")
    @ApiModelProperty(name = "visible", dataType = "Boolean", value = "菜单是否可见")
    private Boolean visible = true;

    /**
     * 菜单的图标
     */
    @Column(name = "icon")
    @ApiModelProperty(name = "icon", dataType = "String", value = "菜单的图标")
    private String icon;

    /**
     * 菜单的状态
     *
     * @see StatusEnum
     */
    @Column(name = "status")
    @ApiModelProperty(name = "status", dataType = "Integer", value = "菜单的状态")
    private Integer status;

    public Menu() {

    }

}

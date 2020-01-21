package com.wisdompoint.platform.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author： ShaoJie
 * @data： 2020年01月20日 16:57
 * @Description： 管理员信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Accessors(chain = true)
@Table(name = "users")
@ApiModel(value = "Users", description = "管理员信息")
public class Users implements Serializable {

    private static final long serialVersionUID = -6659707235921426721L;

    /**
     * 管理员的编号
     */
    @Id
    @NotNull
    @Column(name = "id")
    @ApiModelProperty(name = "id", value = "管理员的编号", dataType = "String")
    private String id;

    /**
     * 管理员的编号
     */
    @NotNull
    @Column(name = "username")
    @ApiModelProperty(name = "username", value = "用户名", dataType = "String")
    private String username;

    /**
     * 管理员的编号
     */
    @NotNull
    @Column(name = "password")
    @ApiModelProperty(name = "password", value = "用户密码", dataType = "String")
    private String password;

    /**
     * 账号是否启用
     */
    @Column(name = "enable")
    @ApiModelProperty(name = "password", value = "账号是否启用", dataType = "Boolean")
    private Boolean enable;

}

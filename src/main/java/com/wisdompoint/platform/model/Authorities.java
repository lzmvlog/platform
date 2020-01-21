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
 * @data： 2020年01月20日 17:06
 * @Description： 权限
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Accessors(chain = true)
@Table(name = "authorities")
@ApiModel(value = "Authorities", description = "摄像头信息")
public class Authorities implements Serializable {

    private static final long serialVersionUID = 4061312531895411970L;

    /**
     * 用户的自增 id
     */
    @Id
    @NotNull
    @Column(name = "id")
    @ApiModelProperty(name = "id", value = "管理员的编号", dataType = "String")
    private Integer id;

    /**
     * 用户的账号
     */
    @Column(name = "member_id")
    @ApiModelProperty(name = "memberId", value = "用户的账号", dataType = "String")
    private Integer memberId;

    /**
     * 用户的权限
     */
    @Column(name = "authority")
    @ApiModelProperty(name = "authority", value = "用户的权限", dataType = "String")
    private String authority;

}

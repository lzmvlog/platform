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

/**
 * @author： ShaoJie
 * @data： 2019年12月31日 10:38
 * @Description： 摄像头信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Accessors(chain = true)
@Table(name = "camera")
@ApiModel(value = "Camera", description = "摄像头信息")
public class Camera {

    /**
     * 摄像头的编号
     */
    @Id
    @NotNull
    @Column(name = "id")
    @ApiModelProperty(name = "id", value = "摄像头的编号", dataType = "String")
    private String id;

    @Column(name = "address")
    @ApiModelProperty(name = "address", value = "摄像头地点", dataType = "String")
    private String address;

    @Column(name = "ip")
    @ApiModelProperty(name = "ip", value = "摄像头ip", dataType = "String")
    private String ip;

    @Column(name = "name")
    @ApiModelProperty(name = "name", value = "摄像头名称", dataType = "String")
    private String name;

    /**
     * 状态
     *
     * @See StatusEnum
     */
    @Column(name = "status")
    @ApiModelProperty(name = "status", value = "状态", dataType = "Integer")
    private Integer status;

}

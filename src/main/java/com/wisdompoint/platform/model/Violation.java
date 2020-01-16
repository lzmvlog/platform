package com.wisdompoint.platform.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wisdompoint.platform.util.em.StatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author： ShaoJie
 * @data： 2019年12月11日 16:31
 * @Description： 违规实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "violation")
@Builder(toBuilder = true)
@Accessors(chain = true)
@ApiModel(value = "violation", description = "违规记录")
public class Violation {

    /**
     * 违规的 id 编号
     *
     */
    @Id
    @Column(name = "id")
    @ApiModelProperty(name = "id", value = "违规的 id 编号", dataType = "String")
    private String id;

    /**
     * 用户的id
     */
    @Column(name = "member_id")
    @ApiModelProperty(name = "memberId", value = "用户的id", dataType = "String")
    private String memberId;

    /**
     * 记录创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "create_time")
    @ApiModelProperty(name = "id", value = "createTime", dataType = "date")
    private Date createTime;

    /**
     * 违规代码
     */
    @Column(name = "code")
    @ApiModelProperty(name = "code", value = "违规代码", dataType = "String")
    private String code;

    /**
     * 违规图片
     */
    @Column(name = "image", columnDefinition = "mediumtext")
    @ApiModelProperty(name = "image", value = "违规图片id", dataType = "String")
    private String image;

    /**
     * 记录是否处理
     */
    @Column(name = "process")
    @ApiModelProperty(name = "process", value = "记录是否处理", dataType = "Integer")
    private Integer process;

    /**
     * 工作票id
     */
    @Column(name = "work_id")
    @ApiModelProperty(name = "workTicketId", value = "工作票id", dataType = "String")
    private String workTicketId;

    /**
     * 摄像头的 ip
     */
    @Column(name = "camera_id")
    @ApiModelProperty(name = "cameraId", value = "摄像头的 ip", dataType = "String")
    private String cameraId;

    /**
     * 状态
     *
     * @see StatusEnum
     */
    @Column(name = "status")
    @ApiModelProperty(name = "status", value = "status", dataType = "Integer")
    private Integer status;

}

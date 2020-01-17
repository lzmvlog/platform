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
 * @data： 2020年01月17日 11:31
 * @Description： 违规记录日志
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "violation_log")
@Builder(toBuilder = true)
@Accessors(chain = true)
@ApiModel(value = "violation_log", description = "违规记录日志")
public class ViolationLog {

    /**
     * 违规的 id 编号
     */
    @Id
    @Column(name = "id")
    @ApiModelProperty(name = "id", value = "违规的 id 编号", dataType = "String")
    private String id;

    /**
     * 记录如何处理
     */
    @Column(name = "process")
    @ApiModelProperty(name = "process", value = "记录如何处理", dataType = "String")
    private String process;

    /**
     * 审核人的id
     */
    @Column(name = "review_id")
    @ApiModelProperty(name = "review_id", value = "审核人的id", dataType = "String")
    private String reviewId;

    /**
     * 处理人的id
     */
    @Column(name = "process_id")
    @ApiModelProperty(name = "processId", value = "处理人的id", dataType = "String")
    private String processId;

    /**
     * 记录的id
     */
    @Column(name = "violation_id")
    @ApiModelProperty(name = "violationId", value = "用户的id", dataType = "String")
    private String violationId;

    /**
     * 记录创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "create_time")
    @ApiModelProperty(name = "id", value = "createTime", dataType = "date")
    private Date createTime;

    /**
     * 状态
     *
     * @see StatusEnum
     */
    @Column(name = "status")
    @ApiModelProperty(name = "status", value = "status", dataType = "Integer")
    private Integer status;

}

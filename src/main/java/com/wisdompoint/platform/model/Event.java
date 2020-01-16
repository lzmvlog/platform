package com.wisdompoint.platform.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
import java.util.Date;

/**
 * @author： ShaoJie
 * @data： 2019年12月26日 14:09
 * @Description： 违规事件
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Accessors(chain = true)
@Table(name = "event")
@ApiModel(value = "Event", description = "违规事件")
public class Event {

    /**
     * 违规事件id
     */
    @Id
    @Column(name = "id")
    @ApiModelProperty(name = "id", value = "违规事件的编号")
    private String id;

    /**
     * 违规内存
     */
    @NotNull(message = "违规内容不能为空")
    @Column(name = "context")
    @ApiModelProperty(name = "context", value = "违规内容")
    private String context;

    /**
     * 违规代码
     */
    @NotNull(message = "违规代码不能为空")
    @Column(name = "code")
    @ApiModelProperty(name = "code", value = "违规代码")
    private String code;

    @NotNull(message = "违规级别不能为空")
    @Column(name = "level")
    @ApiModelProperty(name = "level", value = "违规级别")
    private String level;

    @Column(name = "remark")
    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "create_time")
    @ApiModelProperty(name = "id", value = "createTime", dataType = "date")
    private Date createTime;

    @Column(name = "status")
    @ApiModelProperty(name = "status", value = "状态", dataType = "Integer")
    private Integer status;

}

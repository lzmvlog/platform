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
import java.util.Date;

/**
 * @author： ShaoJie
 * @data： 2019年12月20日 16:02
 * @Description： 工作票
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Accessors(chain = true)
@Table(name = "ticket")
@ApiModel(value = "WorkTicket", description = "工作票")
public class WorkTicket {

    /**
     * 工作票的id
     */
    @Id
    @Column(name = "id")
    @ApiModelProperty(name = "id", value = "工作票id", dataType = "String")
    private String id;

    /**
     * 项目名称
     */
    @Column(name = "project")
    @ApiModelProperty(name = "project", value = "项目名称", dataType = "String")
    private String project;

    /**
     * 工作的地点
     */
    @Column(name = "address")
    @ApiModelProperty(name = "address", value = "用户的id", dataType = "String")
    private String address;

    /**
     * 记录创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "create_time")
    @ApiModelProperty(name = "createTime", value = "创建时间", dataType = "date")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "start_time")
    @ApiModelProperty(name = "startTime", value = "工作开始时间", dataType = "date")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "end_time")
    @ApiModelProperty(name = "endTime", value = "工作结束时间", dataType = "date")
    private Date endTime;

    @Column(name = "create_member_id")
    @ApiModelProperty(name = "createMemberId",value = "创建人信息(创建工作票信息)", dataType = "String")
    private String createMemberId;

    /**
     * 工作员工信息
     */
    @Column(name = "member_id")
    @ApiModelProperty(name = "memberId",value = "用户的id", dataType = "String")
    private String memberId;

    /**
     * 状态
     *
     * @See StatusEnum
     */
    @Column(name = "status")
    @ApiModelProperty(name = "status", value = "status", dataType = "Integer")
    private Integer status;

}

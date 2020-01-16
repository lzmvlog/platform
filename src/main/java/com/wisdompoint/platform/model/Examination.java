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
 * @data： 2019年12月20日 16:17
 * @Description： 考试记录
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Accessors(chain = true)
@Table(name = "examination")
@ApiModel(value = "Examination", description = "考试记录")
public class Examination {

    /**
     * 考试记录的编号
     */
    @Id
    @Column(name = "id")
    @ApiModelProperty(name = "id", value = "考试记录的编号", dataType = "String")
    private String id;

    /**
     * 用户的id
     */
    @Column(name = "member_id")
    @ApiModelProperty(name = "member_id", value = "用户的id", dataType = "String")
    private String memberId;

    /**
     * 记录创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "create_time")
    @ApiModelProperty(name = "id", value = "createTime", dataType = "date")
    private Date createTime;

    /**
     * 分数
     */
    @Column(name = "score")
    @ApiModelProperty(name = "score", dataType = "double", value = "菜单的状态")
    private double score;

    /**
     * 成绩是否合格
     */
    @Column(name = "eligibility", columnDefinition = "bit(1) default 1")
    @ApiModelProperty(name = "eligibility", dataType = "Boolean", value = "菜单是否可见")
    private Boolean eligibility = false;

    /**
     * 考试记录
     *
     * @see com.wisdompoint.platform.util.em.StatusEnum
     */
    @Column(name = "status")
    @ApiModelProperty(name = "status", dataType = "Integer", value = "菜单的状态")
    private Integer status;

}

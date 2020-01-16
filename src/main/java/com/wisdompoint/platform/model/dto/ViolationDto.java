package com.wisdompoint.platform.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author： ShaoJie
 * @data： 2019年12月11日 16:31
 * @Description： 违规实体 dto 对象 这里是为了避免关于 jpa 读取数据时 读取全部的实体
 *              这里缺少了实体的图片 现在数据库里存的是图片的 base64 码 后面需要将图片存到本地
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ViolationDto {

    /**
     * 违规的 id 编号
     */
    private String id;

    /**
     * 用户的id
     */
    private String memberId;

    /**
     * 记录创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 违规代码
     */
    private String code;

    /**
     * 记录是否处理
     */
    private Integer process;

    /**
     * 工作票id
     */
    private String workTicketId;

    /**
     * 状态
     *
     * @See StatusEnum
     */
    private Integer status;

}

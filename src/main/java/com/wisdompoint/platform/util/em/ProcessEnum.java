package com.wisdompoint.platform.util.em;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author： ShaoJie
 * @data： 2019年12月18日 16:10
 * @Description： 处理记录
 */
@Getter
@AllArgsConstructor
public enum ProcessEnum {

    NORMAL(1, "normal"),

    /**
     * 审核
     */
    REVIEW(2, "review"),

    /**
     * 处理
     */
    PROCESSED(3, "processed");

    /**
     * 存入数据库的状态
     */
    private Integer id;

    /**
     * 处理信息
     */
    private String message;

}

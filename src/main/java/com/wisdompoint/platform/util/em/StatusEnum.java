package com.wisdompoint.platform.util.em;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author： ShaoJie
 * @data： 2019年12月17日 14:20
 * @Description： 状态枚举类
 */
@Getter
@AllArgsConstructor
public enum StatusEnum {

    NORMAL(1,"normal"),

    DELETE(0,"delete");

    /**
     * 存入数据库的状态
     */
    private Integer id;

    /**
     * 装态
     */
    private String status;
}

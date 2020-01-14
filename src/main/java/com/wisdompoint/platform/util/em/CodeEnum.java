package com.wisdompoint.platform.util.em;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author： ShaoJie
 * @data： 2019年12月18日 16:10
 * @Description： 状态码描述Enum
 */
@Getter
@AllArgsConstructor
public enum CodeEnum {

    /**
     * 请求成功
     */
    REQUEST_SUCCESS(200, "请求成功"),

    /**
     * 请求错误
     */
    REQUEST_ERROR(400, "请求错误"),

    /**
     * 请求无权限
     */
    REQUEST_NO_PERMISSION(401, "请求无权限"),

    /**
     * 请求被拒绝
     */
    REQUEST_REFUSE(403, "请求被拒绝"),

    /**
     * 请求不存在
     */
    REQUEST_NO_EXISTENCE(404, "请求不存在"),

    /**
     * 请求服务错误
     */
    REQUEST_SERVICE_ERROR(500, "请求服务错误");

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 描述
     */
    private String msg;


}

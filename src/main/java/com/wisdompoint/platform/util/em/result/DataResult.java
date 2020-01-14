package com.wisdompoint.platform.util.em.result;

import com.wisdompoint.platform.util.em.CodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据返回请求参数
 *
 * @ClassName DataResult
 * @Description
 * @Author ShaoJie
 * @Date 2019/12/18 16:13
 **/
@Data
@NoArgsConstructor
@ApiModel("数据返回请求参数")
public class DataResult {

    /**
     * 状态码
     */
    @ApiModelProperty(name = "code", value = "状态码")
    private Integer code;

    /**
     * 信息
     */
    @ApiModelProperty(name = "message", value = "信息")
    private String message;

    /**
     * 数据
     */
    @ApiModelProperty(name = "data", value = "数据")
    private Object data;

    public DataResult(CodeEnum codeEnum, String message) {
        this.code = codeEnum.getCode();
        this.message = message;
    }

    public DataResult(CodeEnum codeEnum, Object data) {
        this.code = codeEnum.getCode();
        this.data = data;
    }

    public DataResult(CodeEnum codeEnum, String message, Object data) {
        this.code = codeEnum.getCode();
        this.message = message;
        this.data = data;
    }
}

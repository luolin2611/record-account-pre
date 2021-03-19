package cn.recordaccount.common.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 请求对象
 * @author rollin
 * @date 2021-02-23 16:16:14
 */
@Data
public class Request<T extends Serializable> implements Serializable {
    /**
     * 校验请求
     */
    @NotNull(message = "CheckRequest 不能为空")
    @JSONField(name = "check_request")
    @JsonProperty("check_request")
    private CheckRequest checkRequest;

    /**
     * 请求数据json
     */
    @Valid
    @NotNull(message = "请求的参数对象不能为空")
    private T body;
}

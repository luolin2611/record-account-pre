package cn.recordaccount.common.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author rollin
 * @date 2021-02-22 14:26:55
 */
@Data
public class CheckRequest {
    /**
     * 时间戳
     */
    @NotNull
    private String timestamps;

    /**
     * 签名
     * sessionId + token + timestamps 使用MD5加密
     */
    @NotNull
    private String sign;
}

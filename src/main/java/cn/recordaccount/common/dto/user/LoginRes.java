package cn.recordaccount.common.dto.user;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author rollin
 * @date 2021-02-22 14:47:53
 */
@Data
public class LoginRes implements Serializable {
    /**
     * 用户ID
     */
    @NotNull
    private Integer userId;
    /**
     * 用户名
     */
    @NotNull
    private String userName;
    /**
     * 昵称
     */
    @NotNull
    private String realName;
    /**
     * 个人简介
     */
    @NotNull
    private String personalResume;
    /**
     * jwt token
     */
    @NotNull
    private String jwtToken;
}

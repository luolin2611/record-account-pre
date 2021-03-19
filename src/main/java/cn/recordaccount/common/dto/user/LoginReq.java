package cn.recordaccount.common.dto.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * @author rollin
 * @date 2021-02-22 14:42:42
 */
@Data
public class LoginReq implements Serializable {
    /**
     * 登录账户
     * 用户名/手机号/邮箱
     */
    @NotNull(message = "登录账户不能为空")
    private String account;

    /**
     * 登录密码
     */
    @NotBlank(message = "登录密码不能为空")
    private String password;
}

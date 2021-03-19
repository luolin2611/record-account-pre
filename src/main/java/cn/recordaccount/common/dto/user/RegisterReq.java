package cn.recordaccount.common.dto.user;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author rollin
 * @date 2021-02-25 17:16:59
 */
@Data
public class RegisterReq implements Serializable {
    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空")
    private String userName;
    /**
     * 手机号
     */
    @NotNull(message = "手机号不能为空")
    private String mobile;
    /**
     * 邮箱
     */
    @NotNull(message = "邮箱不能为空")
    private String email;
    /**
     * 昵称
     */
    @NotNull(message = "昵称不能为空")
    private String realName;
    /**
     * 个人简介
     */
    @NotNull(message = "个人简介不能为空")
    private String personalResume;
    /**
     * 密码不能为空
     */
    @NotNull(message = "密码不能为空")
    private String password;
}

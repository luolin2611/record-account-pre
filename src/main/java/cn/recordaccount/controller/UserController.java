package cn.recordaccount.controller;

import cn.recordaccount.common.dto.Request;
import cn.recordaccount.common.dto.Response;
import cn.recordaccount.common.dto.user.LoginReq;
import cn.recordaccount.common.dto.user.LoginRes;
import cn.recordaccount.common.dto.user.RegisterReq;
import cn.recordaccount.common.dto.user.RegisterRes;
import cn.recordaccount.service.user.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 用户Controller
 * @author rollin
 * @date 2021-02-22 14:54:05
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 登录
     * @param request
     * @return
     */
    @PostMapping("/login")
    public Response<LoginRes> login(@Valid @RequestBody Request<LoginReq> request) {
        return userService.login(request);
    }

    /**
     * 注册用户
     * @param request
     * @return
     */
    @PostMapping("/register")
    public Response<RegisterRes> register(@Valid @RequestBody Request<RegisterReq> request) {
        return userService.register(request);
    }
}

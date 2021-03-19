package cn.recordaccount.service.user;

import cn.recordaccount.common.constant.ApiEnum;
import cn.recordaccount.common.constant.Constant;
import cn.recordaccount.common.constant.ResStatusEnum;
import cn.recordaccount.common.dto.Request;
import cn.recordaccount.common.dto.Response;
import cn.recordaccount.common.dto.user.LoginReq;
import cn.recordaccount.common.dto.user.LoginRes;
import cn.recordaccount.common.dto.user.RegisterReq;
import cn.recordaccount.common.dto.user.RegisterRes;
import cn.recordaccount.common.entity.User;
import cn.recordaccount.common.util.*;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;


/**
 * @author rollin
 * @date 2021-02-25 10:37:28
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * 登录接口
     * @param request
     * @return
     */
    @Override
    public Response<LoginRes> login(Request<LoginReq> request) {
        //1.获取请求地址
        String requestMethod = ApiEnum.USER_LOGIN.getRequestMethod();
        //2.将明文密码转为MD5字符串
        LoginReq loginReq = request.getBody();
        //把密码转为md5
        loginReq.setPassword(MD5Util.MD5(loginReq.getPassword()));
        request.setBody(loginReq);
        // 3.请求服务 进行登录
        Response<LoginRes> response = HttpUtil.getResponse(request, Constant.SERVER_NAME_USER, requestMethod);
        String stateCode = response.getCode();
        if(stateCode.equals(ResStatusEnum.SUCCESS.getCode())) {
            // 4.生成jwt token
            LoginRes res = JSON.parseObject(JSON.toJSONString(response.getBody()), LoginRes.class);
            User user = new User();
            user.setUserId(res.getUserId());
            user.setUserName(res.getUserName());
            String token = JwtUtils.generateToken(user, RSAUtil.getPrivateKey(YamlUtil.getValue("jwt.private-key")));
            res.setJwtToken(token);
            response.setBody(res);
        }
        return response;
    }

    /**
     * 用户注册
     * @param request
     * @return
     */
    @Override
    public Response<RegisterRes> register(Request<RegisterReq> request) {
        //1.获取请求地址
        String requestMethod = ApiEnum.USER_REGISTER.getRequestMethod();
        //2.将明文密码转为MD5字符串
        RegisterReq registerReq = request.getBody();
        registerReq.setPassword(MD5Util.MD5(registerReq.getPassword()));
        request.setBody(registerReq);
        //3.请求服务
        return HttpUtil.getResponse(request, Constant.SERVER_NAME_USER, requestMethod);
    }
}

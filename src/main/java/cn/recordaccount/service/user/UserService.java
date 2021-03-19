package cn.recordaccount.service.user;

import cn.recordaccount.common.dto.Request;
import cn.recordaccount.common.dto.Response;
import cn.recordaccount.common.dto.user.LoginReq;
import cn.recordaccount.common.dto.user.LoginRes;
import cn.recordaccount.common.dto.user.RegisterReq;
import cn.recordaccount.common.dto.user.RegisterRes;

public interface UserService {
    Response<LoginRes> login(Request<LoginReq> request);
    Response<RegisterRes> register(Request<RegisterReq> request);
}

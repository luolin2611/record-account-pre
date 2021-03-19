package cn.recordaccount.service.home;

import cn.recordaccount.common.dto.Request;
import cn.recordaccount.common.dto.Response;
import cn.recordaccount.common.dto.recordaccount.home.HomeInitInfoReq;
import cn.recordaccount.common.dto.recordaccount.home.HomeInitInfoRes;

/**
 * @author rollin
 * @date 2021-03-04 09:41:27
 */
public interface HomeService {
    Response<HomeInitInfoRes> homeInitInfo(Request<HomeInitInfoReq> request);
}

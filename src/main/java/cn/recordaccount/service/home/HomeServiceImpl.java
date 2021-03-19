package cn.recordaccount.service.home;

import cn.recordaccount.common.constant.ApiEnum;
import cn.recordaccount.common.constant.Constant;
import cn.recordaccount.common.dto.Request;
import cn.recordaccount.common.dto.Response;
import cn.recordaccount.common.dto.recordaccount.home.HomeInitInfoReq;
import cn.recordaccount.common.dto.recordaccount.home.HomeInitInfoRes;
import cn.recordaccount.common.util.HttpUtil;
import org.springframework.stereotype.Service;

/**
 * @author rollin
 * @date 2021-03-04 09:41:13
 */
@Service
public class HomeServiceImpl implements HomeService {

    /**
     * Home 页面初始化信息请求
     * @param request
     * @return
     */
    @Override
    public Response<HomeInitInfoRes> homeInitInfo(Request<HomeInitInfoReq> request) {
        //1.获取请求地址
        String requestMethod = ApiEnum.RECORD_ACCOUNT_HOME_INIT_INFO.getRequestMethod();
        //3.请求服务
        return HttpUtil.getResponse(request, Constant.SERVER_NAME_RECORD_ACCOUNT, requestMethod);
    }
}

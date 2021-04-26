package cn.recordaccount.service.report;

import cn.recordaccount.common.constant.ApiEnum;
import cn.recordaccount.common.constant.Constant;
import cn.recordaccount.common.dto.Request;
import cn.recordaccount.common.dto.Response;
import cn.recordaccount.common.dto.recordaccount.report.QueryReportDetailsListReq;
import cn.recordaccount.common.dto.recordaccount.report.QueryReportDetailsListRes;
import cn.recordaccount.common.dto.recordaccount.report.QueryReportInfoReq;
import cn.recordaccount.common.dto.recordaccount.report.QueryReportInfoRes;
import cn.recordaccount.common.util.HttpUtil;
import org.springframework.stereotype.Service;

/**
 * 报表service
 *
 * @author rollin
 * @date 2021-03-30 14:21:34
 */
@Service
public class ReportServiceImpl implements ReportService {
    /**
     * 报表查询信息
     *
     * @param request
     * @return
     */
    @Override
    public Response<QueryReportInfoRes> queryReportInfo(Request<QueryReportInfoReq> request) {
        //1.获取请求地址
        String requestMethod = ApiEnum.REPORT_QUERY_REPORT_INFO.getRequestMethod();
        //2.请求服务
        return HttpUtil.getResponse(request, Constant.SERVER_NAME_RECORD_ACCOUNT, requestMethod);
    }

    /**
     * 请求详情列表
     *
     * @param request
     * @return
     */
    @Override
    public Response<QueryReportDetailsListRes> queryReportDetailsList(Request<QueryReportDetailsListReq> request) {
        //1.获取请求地址
        String requestMethod = ApiEnum.REPORT_QUERY_DETAILS_LIST.getRequestMethod();
        //2.请求服务
        return HttpUtil.getResponse(request, Constant.SERVER_NAME_RECORD_ACCOUNT, requestMethod);
    }
}

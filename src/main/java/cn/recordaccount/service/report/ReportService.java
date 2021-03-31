package cn.recordaccount.service.report;


import cn.recordaccount.common.dto.Request;
import cn.recordaccount.common.dto.Response;
import cn.recordaccount.common.dto.recordaccount.report.QueryReportInfoReq;
import cn.recordaccount.common.dto.recordaccount.report.QueryReportInfoRes;

/**
 * 报表service
 *
 * @author rollin
 * @date 2021-03-30 14:21:34
 */
public interface ReportService {
    /**
     * 报表查询信息
     *
     * @param request
     * @return
     */
    Response<QueryReportInfoRes> queryReportInfo(Request<QueryReportInfoReq> request);
}

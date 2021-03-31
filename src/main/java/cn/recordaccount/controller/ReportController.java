package cn.recordaccount.controller;

import cn.recordaccount.common.dto.Request;
import cn.recordaccount.common.dto.Response;
import cn.recordaccount.common.dto.recordaccount.report.QueryReportInfoReq;
import cn.recordaccount.common.dto.recordaccount.report.QueryReportInfoRes;
import cn.recordaccount.service.report.ReportService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 报表Controller
 *
 * @author rollin
 * @date 2021-03-30 17:43:07
 */
@RestController
@RequestMapping("/report")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/queryReportInfo")
    public Response<QueryReportInfoRes> queryReportInfo(@RequestBody Request<QueryReportInfoReq> request) {
        return reportService.queryReportInfo(request);
    }
}

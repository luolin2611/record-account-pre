package cn.recordaccount.service.bill;

import cn.recordaccount.common.dto.Request;
import cn.recordaccount.common.dto.Response;
import cn.recordaccount.common.dto.recordaccount.bill.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.ResponseEntity;

import java.io.InputStream;

/**
 * @author rollin
 * @date 2021-03-12 10:56:11
 */
public interface BillService {
    Response<QueryBillInfoRes> queryBillInfo(Request<QueryBillInfoReq> request);

    /**
     * 查询 月支出/月收入 列表
     *
     * @return
     */
    Response<QueryMonthIncomeExpenseListRes> queryMonthIncomeExpenseList(Request<QueryMonthIncomeExpenseListReq> request);

    /**
     * 查询年账单折线图数据
     *
     * @param request 请求参数
     * @return 每月的收入和支出list
     */
    Response<QueryYearBrokeLineListRes> queryYearBrokeLineList(Request<QueryYearBrokeLineListReq> request);

    /**
     * 导出账单查询记账信息
     *
     * @param request 请求参数体
     * @return 返回数据
     */
    Workbook billExportQueryRecordAccount(Request<BillExportReq> request);
}

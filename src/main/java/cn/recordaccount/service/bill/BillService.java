package cn.recordaccount.service.bill;

import cn.recordaccount.common.dto.Request;
import cn.recordaccount.common.dto.Response;
import cn.recordaccount.common.dto.recordaccount.bill.QueryBillInfoReq;
import cn.recordaccount.common.dto.recordaccount.bill.QueryBillInfoRes;
import cn.recordaccount.common.dto.recordaccount.bill.QueryMonthIncomeExpenseListReq;
import cn.recordaccount.common.dto.recordaccount.bill.QueryMonthIncomeExpenseListRes;

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
}

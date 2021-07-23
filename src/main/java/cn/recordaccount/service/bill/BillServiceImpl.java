package cn.recordaccount.service.bill;


import cn.recordaccount.common.constant.ApiEnum;
import cn.recordaccount.common.constant.Constant;
import cn.recordaccount.common.dto.Request;
import cn.recordaccount.common.dto.Response;
import cn.recordaccount.common.dto.recordaccount.bill.*;
import cn.recordaccount.common.util.HttpUtil;
import org.springframework.stereotype.Service;

/**
 * 账单 Service
 *
 * @author rollin
 * @date 2021-03-12 10:57:51
 */
@Service
public class BillServiceImpl implements BillService {
    /**
     * 获取账单信息
     * 获取顶部信息根据传入的参数：返回
     * A.月支出/月收入、B.年收入/年支出、C.时间段-收入/支出；
     * AC --> 每日支出/每日收入、B-->返回每月收入支出
     *
     * @param request
     * @return
     */
    @Override
    public Response<QueryBillInfoRes> queryBillInfo(Request<QueryBillInfoReq> request) {
        //1.获取请求地址
        String requestMethod = ApiEnum.BILL_QUERY_BILL_INFO.getRequestMethod();
        //2.请求服务
        return HttpUtil.getResponse(request, Constant.SERVER_NAME_RECORD_ACCOUNT, requestMethod);
    }

    /**
     * 查询 月支出/月收入 列表
     *
     * @param request
     * @return
     */
    @Override
    public Response<QueryMonthIncomeExpenseListRes> queryMonthIncomeExpenseList(Request<QueryMonthIncomeExpenseListReq> request) {
        //1.获取请求地址
        String requestMethod = ApiEnum.BILL_QUERY_MONTH_INCOME_EXPENSE_INFO.getRequestMethod();
        //2.请求服务
        return HttpUtil.getResponse(request, Constant.SERVER_NAME_RECORD_ACCOUNT, requestMethod);
    }

    /**
     * 查询年账单折线图数据
     *
     * @param request 请求参数
     * @return 每月的收入和支出list
     */
    @Override
    public Response<QueryYearBrokeLineListRes> queryYearBrokeLineList(Request<QueryYearBrokeLineListReq> request) {
        //1.获取请求地址
        String requestMethod = ApiEnum.BILL_QUERY_YEAR_BROKE_LINE_LIST.getRequestMethod();
        //2.请求服务
        return HttpUtil.getResponse(request, Constant.SERVER_NAME_RECORD_ACCOUNT, requestMethod);
    }
}

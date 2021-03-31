package cn.recordaccount.controller;

import cn.recordaccount.common.dto.Request;
import cn.recordaccount.common.dto.Response;
import cn.recordaccount.common.dto.recordaccount.bill.*;
import cn.recordaccount.service.bill.BillService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Calendar;

/**
 * 账单Controller
 *
 * @author rollin
 * @date 2021-03-12 10:49:45
 */
@RestController
@RequestMapping("/bill")
public class BillController {
    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    /**
     * 获取账单信息
     * 获取顶部信息根据传入的参数：返回
     * A.月支出/月收入、B.年收入/年支出、C.时间段-收入/支出；
     * AC --> 每日支出/每日收入、B-->返回每月收入支出
     *
     * @param request
     * @return
     */
    @PostMapping("/queryBillInfo")
    public Response<QueryBillInfoRes> queryBillInfo(@RequestBody @Valid Request<QueryBillInfoReq> request) {
        return billService.queryBillInfo(request);
    }

    /**
     * 获取系统时间
     *
     * @return
     */
    @PostMapping("/querySysTime")
    public Response<QuerySysTimeRes> querySysTime() {
        Calendar nowDate = Calendar.getInstance();
        QuerySysTimeRes querySysTimeRes = new QuerySysTimeRes();
        querySysTimeRes.setYear(nowDate.get(Calendar.YEAR));
        querySysTimeRes.setMonth(nowDate.get(Calendar.MONDAY) + 1);
        querySysTimeRes.setDay(nowDate.get(Calendar.DAY_OF_MONTH));
        querySysTimeRes.setWeek(nowDate.get(Calendar.DAY_OF_WEEK));
        querySysTimeRes.setDate(System.currentTimeMillis());
        return Response.success(querySysTimeRes);
    }

    /**
     * 查询 月支出/月收入 列表
     *
     * @return
     */
    @PostMapping("/queryMonthIncomeExpenseList")
    public Response<QueryMonthIncomeExpenseListRes> queryMonthIncomeExpenseList(@RequestBody Request<QueryMonthIncomeExpenseListReq> request){
        return billService.queryMonthIncomeExpenseList(request);
    }
}

package cn.recordaccount.service.bill;


import cn.recordaccount.common.constant.ApiEnum;
import cn.recordaccount.common.constant.Constant;
import cn.recordaccount.common.dto.Request;
import cn.recordaccount.common.dto.Response;
import cn.recordaccount.common.dto.recordaccount.bill.*;
import cn.recordaccount.common.entity.DayRecordAccountObject;
import cn.recordaccount.common.entity.RecordAccount;
import cn.recordaccount.common.entity.ReportInfoObject;
import cn.recordaccount.common.util.ExcelUtils;
import cn.recordaccount.common.util.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 账单 Service
 *
 * @author rollin
 * @date 2021-03-12 10:57:51
 */
@Service
@Slf4j
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

    @Override
    public Workbook billExportQueryRecordAccount(Request<BillExportReq> request) {
        //1.获取请求地址
        String requestMethod = ApiEnum.BILL_QUERY_RECORD_ACCOUNT_INFO_LIST.getRequestMethod();
        //2.请求服务
        Response response = HttpUtil.getResponse(request, Constant.SERVER_NAME_RECORD_ACCOUNT, requestMethod);
        JSONObject jsonObject = JSON.parseObject(response.getBody().toString());
        List<RecordAccount> recordAccounts = jsonObject.getJSONArray("recordAccounts").toJavaList(RecordAccount.class);

        // 3.封装写入Excel的三个对象信息
        List list = new ArrayList();
        // 3.1 第一个sheet数据：放置的是记账的list
        list.add(recordAccounts);
        // 3.2 第二个 sheet 数据，放置的是导出的账本信息
        BillExportReq billExportReq = request.getBody();
        list.add(billExportReq);
        // 3.3 第三个 sheet 数据，放置的是账单报表信息
        if(recordAccounts.size() > 0) {
            // 计算相差时间 单位  年
            Date date1 = recordAccounts.get(recordAccounts.size() - 1).getRecordTime();
            Date date2 = recordAccounts.get(0).getRecordTime();
            int differYear = differYear(date1, date2);
            // 时间范围 yyyyMMdd - yyyyMMdd
            String timeRange = new SimpleDateFormat("yyyyMMdd")
                    .format(date1) + " - " + new SimpleDateFormat("yyyyMMdd").format(date2);
            double totalIncome = recordAccounts.stream()
                    .filter(recordAccount -> "1".equals(recordAccount.getClassifyType()))
                    .collect(Collectors.summingDouble(RecordAccount::getBillMoney));
            double totalExpense = recordAccounts.stream()
                    .filter(recordAccount -> "0".equals(recordAccount.getClassifyType()))
                    .collect(Collectors.summingDouble(RecordAccount::getBillMoney));
            double totalSurplus = totalIncome - totalExpense;

            ReportInfoObject reportInfoObject = ReportInfoObject.builder().totalTime(String.valueOf(differYear))
                    .timeRange(timeRange).totalIncome(totalIncome).totalExpense(totalExpense)
                    .totalSurplus(totalSurplus).exportDate(new Date()).build();
            list.add(reportInfoObject);
        } else {
            // 不需要填充，放置空对象
            list.add(null);
        }
        return ExcelUtils.INSTANCE.getExcelOutputStream(list);
    }


    /**
     * 计算两个日期相差的年份
     *
     * @param date1 小日期
     * @param date2 大日期
     * @return 相差年份
     */
    private Integer differYear(Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(date1);
        calendar2.setTime(date2);
        int surplus = calendar2.get(Calendar.DATE) - calendar1.get(Calendar.DATE);
        int result = calendar2.get(Calendar.MONTH) - calendar1.get(Calendar.MONTH);
        int year = calendar2.get(Calendar.YEAR) - calendar1.get(Calendar.YEAR);
        if (result < 0) {
            result = 1;
        } else if (result == 0) {
            result = surplus <= 0 ? 0 : 1;
        } else {
            result = 0;
        }
        return year + result;
    }

}

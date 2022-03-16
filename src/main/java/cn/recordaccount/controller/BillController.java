package cn.recordaccount.controller;

import cn.recordaccount.common.dto.Request;
import cn.recordaccount.common.dto.Response;
import cn.recordaccount.common.dto.recordaccount.bill.*;
import cn.recordaccount.service.bill.BillService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
    public Response<QueryMonthIncomeExpenseListRes> queryMonthIncomeExpenseList(@RequestBody Request<QueryMonthIncomeExpenseListReq> request) {
        return billService.queryMonthIncomeExpenseList(request);
    }

    /**
     * 查询年账单折线图数据
     *
     * @param request 请求参数
     * @return 每月的收入和支出list
     */
    @PostMapping("/queryYearBrokeLineList")
    public Response<QueryYearBrokeLineListRes> queryYearBrokeLineList(@RequestBody Request<QueryYearBrokeLineListReq> request) {
        return billService.queryYearBrokeLineList(request);
    }

    /**
     * 查询账单导出
     * <p>该下载文件的方式参考链接：</p>
     * <p><a>https://blog.csdn.net/qq_34940644/article/details/99638156</a></p>
     * <p><a>https://www.cnblogs.com/alice-cj/p/10363123.html</a></p>
     *
     * @param request 请求参数
     * @return 返回账单信息
     */
    @PostMapping("/billExport")
    public void billExport(HttpServletResponse response,
                           @Valid @RequestBody Request<BillExportReq> request) throws IOException {
        Workbook wb = billService.billExportQueryRecordAccount(request);
        // 将wb 写入输出流中
        ServletOutputStream outputStream = response.getOutputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        wb.write(byteArrayOutputStream);
        // 响应类型,编码
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        // 设置生成的Excel的文件名
        response.setHeader("Content-Disposition", "attachment;filename=" +
                new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".xlsx");
        // 刷新此输出流并强制将所有缓冲的输出字节被写出
        outputStream.write(byteArrayOutputStream.toByteArray());
        outputStream.flush();
        outputStream.close();
        wb.close();
    }
}

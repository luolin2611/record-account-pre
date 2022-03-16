package cn.recordaccount.common.util;

import cn.recordaccount.common.dto.recordaccount.bill.BillExportReq;
import cn.recordaccount.common.entity.RecordAccount;
import cn.recordaccount.common.entity.ReportInfoObject;
import com.alibaba.fastjson.JSON;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ObjectUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 账单导入导出 Excel 工具类
 * <p>此处使用单例模式，因为该工具类不经常使用，因此没有必要放在容器中浪费资源</p>
 *
 * @author rollin
 * @date 2022-03-05 21:21:54
 */
public enum ExcelUtils {

    INSTANCE;

    /**
     * 获取Excel 文件流
     *
     * @param list 填充文件的数据
     */
    public Workbook getExcelOutputStream(List list) {
        Workbook wb = new XSSFWorkbook();
        // 1.初始化excel 头部和sheet list
        List excelHeadSheetList = new ArrayList<Map<String, List<String>>>();
        initExportFileTemplate(excelHeadSheetList);

        for (int i = 0; i < excelHeadSheetList.size(); i++) {
            Map<String, List<String>> map = (Map<String, List<String>>) excelHeadSheetList.get(i);
            // 2.处理 sheet、头部、内容
            traverseMapHandlerHeadShMapeetContent(map, list, wb);
        }
        return wb;
    }

    /**
     * 初始化 excel头部 和  sheet 字符串 的list
     */
    private void initExportFileTemplate(List<Map<String, List<String>>> excelHeadSheetList) {
        // data form ==> {"账单详情": "序号", "账单金额", "账单分类" ...}
        Map map = new HashMap<String, List<String>>();
        map.put("账单详情", Arrays.asList("序号", "账单金额", "账单分类", "账单类型", "账单描述", "记账时间"));
        excelHeadSheetList.add(map);
        map = new HashMap<String, List<String>>();
        map.put("导出信息", Arrays.asList("导出类型", "年份", "月份", "开始时间", "结束时间", "导出时间"));
        excelHeadSheetList.add(map);
        map = new HashMap<String, List<String>>();
        map.put("账单报表", Arrays.asList("共计时间(年)", "时间范围", "收入总额", "支出总额", "总结余", "统计时间"));
        excelHeadSheetList.add(map);
    }

    /**
     * 遍历map处理 单个的 sheet、头部、内容
     *
     * @param map  sheet的map 对象
     * @param list 存储数据的list
     * @param wb   Workbook 对象
     */
    private void traverseMapHandlerHeadShMapeetContent(Map<String, List<String>> map, List list, Workbook wb) {
        Iterator<Map.Entry<String, List<String>>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, List<String>> item = iter.next();
            String key = item.getKey();
            List<String> value = item.getValue();
            handlerHeadSheepContent(key, value, list, wb);
        }
    }

    /**
     * 处理单元格数据
     *
     * @param key   作为sheet名称
     * @param value 每列标题
     * @param list  数据集合
     * @param wb    Workbook 对象
     */
    private void handlerHeadSheepContent(String key, List<String> value, List list, Workbook wb) {
        Sheet sheet = wb.createSheet(key);
        // 1.处理头部内容（第一行）
        handlerHeaderSheet(sheet, value, wb);
        // 处理公共样式，为水平居中且实现四周
        CellStyle cellStyle = wb.createCellStyle();
        setHorizontalCenterBorderCellStyle(cellStyle);
        // 2.处理内容部分
        // 2.1 账单详情
        if ("账单详情".equals(key)) {
            fillBillDetailContent(sheet, (List) list.get(0), cellStyle);
        }
        // 2.2 导出信息
        if ("导出信息".equals(key)) {
            fillExportInfo(sheet, (BillExportReq) list.get(1), cellStyle);
        }
        // 2.3 账单报表
        if ("账单报表".equals(key)) {
            fillReportInfo(sheet, (ReportInfoObject) list.get(2), cellStyle);
        }
    }

    /**
     * 填充 导出账单 数据 sheet 页
     *
     * @param sheet     sheet
     * @param list      填充list
     * @param cellStyle 单元格样式
     */
    private void fillBillDetailContent(Sheet sheet, List list, CellStyle cellStyle) {
        // Note, the maximum number of unique fonts in a workbook is limited to 32767.
        // You should re-use fonts in your applications instead of creating a font for each cell.
        for (int i = 1; i <= list.size(); i++) {
            RecordAccount recordAccount = (RecordAccount) list.get(i - 1);
            Row row = sheet.createRow(i);
            // 序号
            Cell cell = row.createCell(0);
            cell.setCellValue(i);
            cell.setCellStyle(cellStyle);
            // 账单金额
            cell = row.createCell(1);
            cell.setCellValue(recordAccount.getBillMoney());
            cell.setCellStyle(cellStyle);
            // 账单分类
            cell = row.createCell(2);
            cell.setCellValue(recordAccount.getClassifyName());
            cell.setCellStyle(cellStyle);
            // 账单类型
            cell = row.createCell(3);
            cell.setCellValue("0".equals(recordAccount.getClassifyType()) ? "支出" : "收入");
            cell.setCellStyle(cellStyle);
            // 账单描述
            cell = row.createCell(4);
            cell.setCellValue(recordAccount.getRemark());
            cell.setCellStyle(cellStyle);
            // 记账时间
            cell = row.createCell(5);
            cell.setCellValue(new SimpleDateFormat("yyyy/MM/dd").format(recordAccount.getRecordTime()));
            cell.setCellStyle(cellStyle);
        }
    }

    /**
     * 填充 账单详情 数据 sheet 页
     *
     * @param sheet     sheet
     * @param exportReq 填充 对象
     * @param cellStyle 单元格样式
     */
    private void fillExportInfo(Sheet sheet, BillExportReq exportReq, CellStyle cellStyle) {
        Row row = sheet.createRow(1);
        Cell cell = row.createCell(0);
        // 导出类型
        cell.setCellValue("year".equals(exportReq.getExportType()) ? "年账单" :
                "month".equals(exportReq.getExportType()) ? "月账单" : "时间段");
        cell.setCellStyle(cellStyle);
        // 年份
        cell = row.createCell(1);
        cell.setCellValue(exportReq.getYear());
        cell.setCellStyle(cellStyle);
        // 月份
        cell = row.createCell(2);
        cell.setCellValue(exportReq.getMonth());
        cell.setCellStyle(cellStyle);
        // 开始时间
        cell = row.createCell(3);
        cell.setCellValue(exportReq.getStartDate());
        cell.setCellStyle(cellStyle);
        // 结束时间
        cell = row.createCell(4);
        cell.setCellValue(exportReq.getEndDate());
        cell.setCellStyle(cellStyle);
        // 导出时间
        cell = row.createCell(5);
        cell.setCellValue(new SimpleDateFormat("yyyy 年 MM 月 dd 日 HH:mm:ss").format(new Date()));
        cell.setCellStyle(cellStyle);
    }

    /**
     * 填充 账单报表 内容
     *
     * @param sheet            sheet
     * @param reportInfoObject 填充对象
     * @param cellStyle        单元格样式
     */
    private void fillReportInfo(Sheet sheet, ReportInfoObject reportInfoObject, CellStyle cellStyle) {
        if (ObjectUtils.isEmpty(reportInfoObject)) {
            // 空对象不需要填充
            return;
        }

        Row row = sheet.createRow(1);
        Cell cell = row.createCell(0);
        // 共计时间
        cell.setCellValue(reportInfoObject.getTotalTime());
        cell.setCellStyle(cellStyle);
        // 时间范围
        cell = row.createCell(1);
        cell.setCellValue(reportInfoObject.getTimeRange());
        cell.setCellStyle(cellStyle);
        // 总收入
        cell = row.createCell(2);
        cell.setCellValue(reportInfoObject.getTotalIncome());
        cell.setCellStyle(cellStyle);
        // 总支出
        cell = row.createCell(3);
        cell.setCellValue(reportInfoObject.getTotalExpense());
        cell.setCellStyle(cellStyle);
        // 总结余
        cell = row.createCell(4);
        cell.setCellValue(reportInfoObject.getTotalSurplus());
        cell.setCellStyle(cellStyle);
        // 导出时间
        cell = row.createCell(5);
        cell.setCellValue(new SimpleDateFormat("yyyy 年 MM 月 dd 日 HH:mm:ss")
                .format(reportInfoObject.getExportDate()));
        cell.setCellStyle(cellStyle);
    }

    /**
     * 处理单元格头部内容，也就是第一行
     *
     * @param sheet 当前sheet
     * @param value 第一行，标题内容
     * @param wb    Workbook对象
     */
    private void handlerHeaderSheet(Sheet sheet, List<String> value, Workbook wb) {
        sheet.createFreezePane(0, 1);
        Row row = sheet.createRow(0);
        for (int i = 0; i < value.size(); i++) {
            // 1.设置每列的宽度，此处 20 * 256 表示， 宽度设置为 20字符的宽度，每个字符宽度为256
            sheet.setColumnWidth(i, 30 * 256);
            CellStyle cellStyle = wb.createCellStyle();
            // 2.设置单元格字体 "黑体，12"
            Font font = wb.createFont();
            font.setFontName("黑体");
            font.setFontHeightInPoints((short) 12);
            cellStyle.setFont(font);
            // 3.设置单元格水平居中，四周实现且固定头部
            setHorizontalCenterBorderCellStyle(cellStyle); // 水平居中，四周实线的单元格
            Cell cell = row.createCell(i);
            cell.setCellValue(value.get(i));
            cell.setCellStyle(cellStyle);
        }
    }

    /**
     * 设置单元格样式，水平居中，四周实线的单元格
     *
     * @param cellStyle cellStyle对象
     */
    private void setHorizontalCenterBorderCellStyle(CellStyle cellStyle) {
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
    }
}

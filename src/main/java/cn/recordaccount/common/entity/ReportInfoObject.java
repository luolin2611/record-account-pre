package cn.recordaccount.common.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * 账单导出的时候报表信息对象
 *
 * @author rollin
 * @date 2022-03-15 22:36:20
 */
@Data
@Builder
public class ReportInfoObject {
    /**
     * 共计时间(年)
     */
    private String totalTime;

    /**
     * 时间范围 (eg: yyyyMMdd - yyyyMMdd)
     */
    private String timeRange;

    /**
     * 总收入
     */
    private Double totalIncome;

    /**
     * 总支出
     */
    private Double totalExpense;

    /**
     * 总结余
     */
    private Double totalSurplus;

    /**
     * 导出时间
     */
    private Date exportDate;
}

package cn.recordaccount.common.dto.recordaccount.report;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 报表
 *
 * @author rollin
 * @date 2021-03-30 14:24:40
 */
@Data
public class QueryReportInfoReq implements Serializable {
    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空")
    private String userId;
    /**
     * 报表类型
     * 0-年,1-月,2-时间段
     */
    @NotNull(message = "报表类型不能为空")
    private String reportType;
    /**
     * 消费类型
     * 0 - 支出 , 1 - 收入
     */
    @NotNull(message = "消费类型不能为空")
    private String type;
    /**
     * 年份
     * 当第一个参数为0的时候必须填充（eg: 2021）
     */
    private String year;
    /**
     * 月份
     * 当第一个参数为1的时候必须填充，格式：yyyyMM（eg: 202101）
     */
    private String month;
    /**
     * 开始时间
     * 当第一个参数为2的时候必须填充，格式：yyyyMMdd (eg: 20210121)
     */
    private String startDate;
    /**
     * 结束时间
     * 当第一个参数为2的时候必须填充，格式：yyyyMMdd (eg: 20210121)
     */
    private String endDate;
}

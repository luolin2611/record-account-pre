package cn.recordaccount.common.dto.recordaccount.bill;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 账单导出请求参数体
 *
 * @author rollin
 * @date 2022-03-05 23:36:13
 */
@Data
public class BillExportReq implements Serializable {
    /**
     * 导出类型 (year、month、range 分别表示：查询年账单、查询月账单、查询时间段)
     */
    @NotNull(message = "账单类型不能为空")
    @Pattern(regexp = "year|month|range", message = "请输入合规的账单类型")
    private String exportType;

    /**
     * 年值 （yyyy） 当billType 为 year 和 month 时不能为空
     */
    private String year;

    /**
     * 月值 （MM） 当billType为 month 时不能为空
     */
    private String month;

    /**
     * 开始时间 （yyyyMMdd） 当billType 为 range时不能为空
     */
    private String startDate;

    /**
     * 结束时间 （yyyyMMdd） 当billType 为 range时不能为空
     */
    private String endDate;

    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空")
    private String userId;
}

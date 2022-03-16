package cn.recordaccount.common.dto.recordaccount.persional;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 账单导出请求对象
 *
 * @author rollin
 * @date 2022-02-28 22:12:38
 */
@Data
public class BillExportReq implements Serializable {

    /**
     * 导出类型 (year - 年、month - 月、range - 范围)
     */
    @NotNull(message = "导出类型不能为空")
    String exportType;

    /**
     * 选择的年份
     */
    String selectYear;

    /**
     * 选择的月份
     */
    String selectMonth;

    /**
     * 开始时间
     */
    String startTime;

    /**
     * 结束时间
     */
    String endTime;
}

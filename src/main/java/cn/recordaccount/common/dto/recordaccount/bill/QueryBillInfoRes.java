package cn.recordaccount.common.dto.recordaccount.bill;

import cn.recordaccount.common.entity.DayRecordAccount;
import cn.recordaccount.common.entity.YearBillDetail;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 账单首页初始化信息
 *
 * @author luolin
 * @date 2021-01-21 23:29:49
 */
@Data
public class QueryBillInfoRes implements Serializable {
    /**
     * 支出
     */
    @NotNull
    private double expense;
    /**
     * 收入
     */
    @NotNull
    private double income;

    /**
     * 年账单
     */
    private YearBillDetail yearBillDetail;
    /**
     * 月账单
     */
    private List<DayRecordAccount> monthBillDetailList;
    /**
     * 时间段账单
     */
    private List<DayRecordAccount> periodBillDetailList;
}
package cn.recordaccount.common.dto.recordaccount.bill;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 查询 月支出/月收入 列表
 *
 * @author rollin
 * @date 2021-03-29 17:40:38
 */
@Data
public class QueryMonthIncomeExpenseListReq implements Serializable {
    /**
     * 查询年月（yyyyMM） - 例如： 202103
     */
    @NotNull(message = "查询年月不能为空")
    private String queryYearMonthTime;
    /**
     * 查询类型
     * 0 - 支出
     * 1 - 收入
     */
    @NotNull(message = "查询类型不能为空")
    private String type;
    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空")
    private String userId;

}

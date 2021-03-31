package cn.recordaccount.common.dto.recordaccount.bill;

import cn.recordaccount.common.entity.QueryMonthIncomeExpenseObject;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;
import java.util.List;

/**
 * 查询 月支出/月收入 列表
 *
 * @author rollin
 * @date 2021-03-29 17:40:38
 */
@Data
public class QueryMonthIncomeExpenseListRes implements Serializable {
    public QueryMonthIncomeExpenseListRes() {
    }

    /**
     * 返回带有请求月的账单信息
     */
    @NonNull
    private List<QueryMonthIncomeExpenseObject> list;
}



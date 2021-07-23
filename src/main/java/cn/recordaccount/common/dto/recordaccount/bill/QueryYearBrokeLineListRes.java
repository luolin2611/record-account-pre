package cn.recordaccount.common.dto.recordaccount.bill;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 查询年账单折线图数据 -- Response
 *
 * @author rollin
 * @date 2021-07-23 22:01:15
 */
@Data
public class QueryYearBrokeLineListRes implements Serializable {

    /**
     * 每月收入List
     */
    @NotNull
    private List<String> incomeList;

    /**
     * 每月支出List
     */
    @NotNull
    private List<String> expenseList;
}

package cn.recordaccount.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 * 当日记账对象
 *
 * @author luolin
 * @date 2021-01-20 23:59:47
 */
@Data
public class DayRecordAccount implements Serializable {
    /**
     * 日期str
     * 例如： [ 1月20 今天 ], [ 1月19 星期二 ]
     */
    private String dateStr;
    /**
     * 当天支出总额
     */
    private double dayExpense;
    /**
     * 当日记账对象集合
     */
    private List<DayRecordAccountObject> dayRecordAccountObjects;
}
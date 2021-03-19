package cn.recordaccount.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 年账单明细
 * 返回每月的：收入、支出、结余以及当月的单个对象数组
 *
 * @author luolin
 * @date 2021-01-23 11:47:47
 */
@Data
public class YearBillDetail implements Serializable {
    /**
     * 年账单明细
     */
    private List<YearBillDetailObject> yearBillDetailObjectList;
}

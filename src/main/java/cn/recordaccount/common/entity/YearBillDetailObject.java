package cn.recordaccount.common.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 年账单明细
 * 返回每月的：收入、支出、结余以及当月的单个对象数组
 *
 * @author luolin
 * @date 2021-01-23 11:56:14
 */
@Data
public class YearBillDetailObject implements Serializable {
    /**
     * 年：eg: 2021
     */
    @NotNull
    private String year;
    /**
     * 月份：eg: 1、12
     */
    @NotNull
    private String month;
    /**
     * 收入
     */
    @NotNull
    private double income;
    /**
     * 支出
     */
    @NotNull
    private double expense;
    /**
     * 结余
     */
    @NotNull
    private double surplus;
}

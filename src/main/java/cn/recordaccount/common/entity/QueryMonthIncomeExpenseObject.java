package cn.recordaccount.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 返回整月的单个对象
 *
 * @author rollin
 * @date 2021-03-29 21:49:40
 */
@Data
public class QueryMonthIncomeExpenseObject implements Serializable {
    private String time;
    private double money;
}
package cn.recordaccount.common.dto.recordaccount.bill;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author rollin
 * @date 2021-03-13 11:23:31
 */
@Data
public class QuerySysTimeRes implements Serializable {
    /**
     * 年
     */
    @NotNull
    private int year;
    /**
     * 月
     */
    @NotNull
    private int month;
    /**
     * 日
     */
    @NotNull
    private int day;
    /**
     * 星期几
     */
    private int week;
    /**
     * 当前时间对象的毫秒数
     */
    @NotNull
    private long date;
}

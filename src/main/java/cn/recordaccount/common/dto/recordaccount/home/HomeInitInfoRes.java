package cn.recordaccount.common.dto.recordaccount.home;

import cn.recordaccount.common.entity.DayRecordAccount;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 首页返回
 *
 * @author luolin
 * @date 2021-01-20 23:46:40
 */
@Data
public class HomeInitInfoRes implements Serializable {
    /**
     * 本月总支出
     */
    @NotNull
    private double monthOutTotal;
    /**
     * 本月总收入
     */
    @NotNull
    private double monthInTotal;
    /**
     * 此处的需求是：最近三日记账对象
     */
    @NotNull
    private List<DayRecordAccount> threedayRecordAccount;
    /**
     * 近三日账单总数
     */
    @NotNull
    private int billNum;
}

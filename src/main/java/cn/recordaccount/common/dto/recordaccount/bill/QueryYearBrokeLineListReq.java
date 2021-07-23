package cn.recordaccount.common.dto.recordaccount.bill;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 查询年账单折线图数据 -- Request
 *
 * @author rollin
 * @date 2021-07-23 22:01:37
 */
@Data
public class QueryYearBrokeLineListReq implements Serializable {
    /**
     * 查询的年
     */
    @NotNull(message = "查询的年份不能为空")
    private String year;
}

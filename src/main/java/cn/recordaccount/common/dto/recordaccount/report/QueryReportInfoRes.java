package cn.recordaccount.common.dto.recordaccount.report;

import cn.recordaccount.common.entity.QueryReportInfoObject;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 报表
 *
 * @author rollin
 * @date 2021-03-30 14:24:40
 */
@Data
public class QueryReportInfoRes implements Serializable {
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
     * 占比列表
     */
    @NotNull
    private List<QueryReportInfoObject> reportInfoList;

}

package cn.recordaccount.common.dto.recordaccount.report;

import cn.recordaccount.common.entity.DayRecordAccountObject;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 请求详情列表
 *
 * @author rollin
 * @date 2021-04-25 17:07:49
 */
@Data
public class QueryReportDetailsListRes implements Serializable {
    /**
     * 时间段账单
     */
    private List<DayRecordAccountObject> dayRecordAccountObjects;
}
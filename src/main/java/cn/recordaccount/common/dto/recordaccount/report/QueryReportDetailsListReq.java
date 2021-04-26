package cn.recordaccount.common.dto.recordaccount.report;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 请求详情列表
 *
 * @author rollin
 * @date 2021-04-25 17:07:49
 */
@Data
public class QueryReportDetailsListReq implements Serializable {
    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空")
    private String userId;
    /**
     * 账单类型
     * 0-年,1-月,2-时间段
     */
    @NotNull(message = "账单类型不能为空")
    private String reportType;
    /**
     * 分类ID
     */
    @NotNull(message = "类别ID不能为空")
    private String classifyId;
    /**
     * 排序类型
     * money - 按照金额排序
     * time - 按照时间排序
     */
    private String orderByType;
    /**
     * 年份
     * 当第一个参数为0的时候必须填充（eg: 2021）
     */
    private String year;
    /**
     * 月份
     * 当第一个参数为1的时候必须填充，格式：yyyyMM（eg: 202101）
     */
    private String month;
    /**
     * 开始时间
     * 当第一个参数为2的时候必须填充，格式：yyyyMMdd (eg: 20210121)
     */
    private String startDate;
    /**
     * 结束时间
     * 当第一个参数为2的时候必须填充，格式：yyyyMMdd (eg: 20210121)
     */
    private String endDate;
    /**
     * 请求页索引 （从1开始表示第一页）- 在代码里面做了减法处理
     */
    private int startPage;
    /**
     * 页码大小
     */
    private int pageSize;
}

package cn.recordaccount.common.entity;

import lombok.Data;

/**
 * 查询报表 info 实体类
 *
 * @author rollin
 * @date 2021-03-30 14:37:15
 */
@Data
public class QueryReportInfoObject {
    /**
     * 类别ID
     */
    private String classifyId;
    /**
     * 类别名
     */
    private String classifyName;
    /**
     * 图标名
     */
    private String iconName;
    /**
     * 占比
     */
    private double proportion;
    /**
     * 笔数
     */
    private String recordCount;
    /**
     * 对应的总额
     */
    private double money;

}

package cn.recordaccount.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 记账对象
 * @author luolin
 * @date 2021-01-21 11:34:02
 */
@Data
public class DayRecordAccountObject implements Serializable {
    /**
     * 记账ID
     */
    private Integer recordAccountId;
    /**
     * 分类ID
     */
    private Integer classifyId;
    /**
     * 分类名称
     */
    private String classifyName;
    /**
     * 支出类型：0-支出,1-收入
     */
    private String classifyType;
    /**
     * 描述信息
     */
    private String remark;
    /**
     * 账单金额
     */
    private String billMoney;
    /**
     * 图标对应图标
     */
    private Icon icon;
    /**
     * 记账时间
     */
    private Date recordTime;
}
package cn.recordaccount.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 记账实体
 *
 * @author luolin
 * @date 2021-01-16 16:19:36
 */
@Data
public class RecordAccount implements Serializable {
    /**
     * 记账ID
     */
    private Integer recordAccountId;
    /**
     * 账单金额
     */
    private Double billMoney;
    /**
     * 分类ID
     */
    private Integer classifyId;
    /**
     * 分类名称
     */
    private String classifyName;
    /**
     * 分类类型
     * 0-支出，1-收入
     */
    private String classifyType;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 账单描述
     */
    private String remark;
    /**
     * 记账时间-创建创建时间
     */
    private Date recordTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}

package cn.recordaccount.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author luolin
 * @date 2021-01-16 16:29:41
 */
@Data
public class Icon implements Serializable {
    /**
     * 图标ID
     */
    private Integer iconId;
    /**
     * 图标名称
     */
    private String iconName;
    /**
     * 更新时间
     */
    private Date updateTime;
}

package cn.recordaccount.common.dto.recordaccount.record;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 查询分类
 * @author rollin
 * @date 2021-03-17 13:55:37
 */
@Data
public class QueryClassifyReq implements Serializable {
    /**
     * 用户ID
     * 用户userId 为 '' 时，查询所有的系统默认的图标
     */
    private String userId;
    /**
     * 查询类型
     * 0-支出
     * 1-收入
     */
    @NotNull
    private String type;
}

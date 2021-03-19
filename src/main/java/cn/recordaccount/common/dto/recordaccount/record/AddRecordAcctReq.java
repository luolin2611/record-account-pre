package cn.recordaccount.common.dto.recordaccount.record;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 添加记账
 *
 * @author rollin
 * @date 2021-03-17 10:15:02
 */
@Data
public class AddRecordAcctReq implements Serializable {
    /**
     * 账单金额
     */
    @NotNull(message = "账单金额不能为空")
    private String billMoney;
    /**
     * 分类ID
     */
    @NotNull(message = "分类ID不能为空")
    private String classifyId;
    /**
     * 分类名称
     */
    @NotNull(message = "分类名称不能为空")
    private String classifyName;
    /**
     * 分类类型
     * 0 - 支出
     * 1 - 收入
     */
    @NotNull(message = "分类类型不能为空")
    private String classifyType;
    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空")
    private String userId;
    /**
     * 描述信息
     */
    @NotNull(message = "描述信息不能为空")
    private String remark;

}

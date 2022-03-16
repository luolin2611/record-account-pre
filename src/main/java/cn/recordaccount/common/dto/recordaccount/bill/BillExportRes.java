package cn.recordaccount.common.dto.recordaccount.bill;

import cn.recordaccount.common.entity.RecordAccount;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 账单导出响应体
 *
 * @author rollin
 * @date 2022-03-05 23:35:51
 */
@Data
@AllArgsConstructor
public class BillExportRes implements Serializable {
    /**
     * 记账账单信息
     */
    @NotNull
    private List<RecordAccount> recordAccounts;
}

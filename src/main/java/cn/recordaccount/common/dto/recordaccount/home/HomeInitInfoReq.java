package cn.recordaccount.common.dto.recordaccount.home;

import cn.recordaccount.common.entity.DayRecordAccount;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 首页返回
 *
 * @author luolin
 * @date 2021-01-20 23:46:40
 */
@Data
public class HomeInitInfoReq implements Serializable {
    /**
     * 用户ID
     */
    @NotNull(message = "User Id 不能为空")
    private String userId;
}

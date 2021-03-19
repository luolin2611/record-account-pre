package cn.recordaccount.common.dto.recordaccount.record;

import cn.recordaccount.common.entity.Classify;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 查询分类
 * @author rollin
 * @date 2021-03-17 13:56:19
 */
@Data
public class QueryClassifyRes implements Serializable {
    /**
     * 分类列表
     * [
     *      [{}, {}, {}, {}],
     *      [{}, {}, {}, {}]
     * ]
     */
    @NotNull
    private List<List<Classify>> classifyList;
}

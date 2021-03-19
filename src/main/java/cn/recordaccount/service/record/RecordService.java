package cn.recordaccount.service.record;

import cn.recordaccount.common.dto.Request;
import cn.recordaccount.common.dto.Response;
import cn.recordaccount.common.dto.recordaccount.record.AddRecordAcctReq;
import cn.recordaccount.common.dto.recordaccount.record.AddRecordAcctRes;
import cn.recordaccount.common.dto.recordaccount.record.QueryClassifyReq;
import cn.recordaccount.common.dto.recordaccount.record.QueryClassifyRes;

public interface RecordService {
    /**
     * 查询所有分类
     * [
     * [{}, {}, {}, {}],
     * [{}, {}, {}, {}]
     * ]
     *
     * @param request
     * @return
     */
    Response<QueryClassifyRes> queryClassify(Request<QueryClassifyReq> request);

    Response<AddRecordAcctRes> addRecordAcct(Request<AddRecordAcctReq> request);
}

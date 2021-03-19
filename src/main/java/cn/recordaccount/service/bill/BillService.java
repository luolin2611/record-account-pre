package cn.recordaccount.service.bill;

import cn.recordaccount.common.dto.Request;
import cn.recordaccount.common.dto.Response;
import cn.recordaccount.common.dto.recordaccount.bill.QueryBillInfoReq;
import cn.recordaccount.common.dto.recordaccount.bill.QueryBillInfoRes;

/**
 * @author rollin
 * @date 2021-03-12 10:56:11
 */
public interface BillService {
    Response<QueryBillInfoRes> queryBillInfo(Request<QueryBillInfoReq> request);
}

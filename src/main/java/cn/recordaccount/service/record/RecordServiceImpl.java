package cn.recordaccount.service.record;

import cn.recordaccount.common.constant.ApiEnum;
import cn.recordaccount.common.constant.Constant;
import cn.recordaccount.common.constant.ResStatusEnum;
import cn.recordaccount.common.dto.Request;
import cn.recordaccount.common.dto.Response;
import cn.recordaccount.common.dto.recordaccount.record.AddRecordAcctReq;
import cn.recordaccount.common.dto.recordaccount.record.AddRecordAcctRes;
import cn.recordaccount.common.dto.recordaccount.record.QueryClassifyReq;
import cn.recordaccount.common.dto.recordaccount.record.QueryClassifyRes;
import cn.recordaccount.common.entity.Classify;
import cn.recordaccount.common.util.HttpUtil;
import cn.recordaccount.common.util.ListUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 记账 Service
 *
 * @author rollin
 * @date 2021-03-17 14:46:32
 */
@Service
public class RecordServiceImpl implements RecordService {

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
    @Override
    public Response<QueryClassifyRes> queryClassify(Request<QueryClassifyReq> request) {
        //1.获取请求地址
        String requestMethod = ApiEnum.RECORD_ACCOUNT_QUERY_CLASSIFY.getRequestMethod();
        //2.请求服务
        Response res = HttpUtil.getResponse(request, Constant.SERVER_NAME_RECORD_ACCOUNT, requestMethod);
        if (res.getCode().equals(ResStatusEnum.SUCCESS.getCode())) {
            // 3. 拿到返回的值
            JSONObject object = JSON.parseObject(JSON.toJSONString(res.getBody()));
            String listStr = object.getString("classifyList");
            // 4.拼接遍历的值 [{}, {}]  ==> [ [{}, {}],[{}, {}] ]
            List<Classify> classifyList = JSON.parseArray(listStr, Classify.class);
            List<List<Classify>> rawClassifyList = ListUtil.partition(classifyList, 10);
            // 5. 组装返回
            QueryClassifyRes queryAllIconRes = new QueryClassifyRes();
            queryAllIconRes.setClassifyList(rawClassifyList);
            res = Response.success(queryAllIconRes);
        }
        return res;
    }

    @Override
    public Response<AddRecordAcctRes> addRecordAcct(Request<AddRecordAcctReq> request) {
        //1.获取请求地址
        String requestMethod = ApiEnum.RECORD_ACCOUNT_RECORD_ACCT.getRequestMethod();
        //2.请求服务
        return HttpUtil.getResponse(request, Constant.SERVER_NAME_RECORD_ACCOUNT, requestMethod);
    }
}

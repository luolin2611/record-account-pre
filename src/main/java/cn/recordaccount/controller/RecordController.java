package cn.recordaccount.controller;

import cn.recordaccount.common.dto.Request;
import cn.recordaccount.common.dto.Response;
import cn.recordaccount.common.dto.recordaccount.record.AddRecordAcctReq;
import cn.recordaccount.common.dto.recordaccount.record.AddRecordAcctRes;
import cn.recordaccount.common.dto.recordaccount.record.QueryClassifyReq;
import cn.recordaccount.common.dto.recordaccount.record.QueryClassifyRes;
import cn.recordaccount.service.record.RecordService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 记账Controller
 *
 * @author rollin
 * @date 2021-03-15 16:01:09
 */
@RestController
@RequestMapping("/record")
public class RecordController {
    private final RecordService recordService;

    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    /**
     * 查询用户分类
     * 根据传入的参数userId 是否为空进行查询不同的分类：
     * 1. 如果userId不为空，查询用户的分类
     * 2. 如果为空查询系统默认分类
     *
     * @param request
     * @return
     */
    @PostMapping("/queryClassify")
    public Response<QueryClassifyRes> queryClassify(@RequestBody @Valid Request<QueryClassifyReq> request) {
        return recordService.queryClassify(request);
    }

    /**
     * 添加记账
     *
     * @param request
     * @return
     */
    @PostMapping("/addRecordAcct")
    public Response<AddRecordAcctRes> addRecordAcct(@RequestBody @Valid Request<AddRecordAcctReq> request) {
        return recordService.addRecordAcct(request);
    }
}

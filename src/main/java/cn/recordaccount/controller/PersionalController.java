package cn.recordaccount.controller;

import cn.recordaccount.common.dto.Response;
import cn.recordaccount.common.dto.recordaccount.persional.BillExportReq;
import cn.recordaccount.common.dto.recordaccount.persional.BillExportRes;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 个人中心 Controller
 *
 * @author rollin
 * @date 2022-02-28 21:48:23
 */
@RestController
@RequestMapping("/my")
public class PersionalController {


    @PostMapping("/billExport")
    public Response<BillExportRes> billExport(@Valid @RequestBody BillExportReq req) {

        return null;
    }
}

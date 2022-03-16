package cn.recordaccount.controller;

import cn.recordaccount.common.dto.Request;
import cn.recordaccount.common.dto.Response;
import cn.recordaccount.common.dto.recordaccount.home.HomeInitInfoReq;
import cn.recordaccount.common.dto.recordaccount.home.HomeInitInfoRes;
import cn.recordaccount.service.home.HomeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Home Controller
 *
 * @author rollin
 * @date 2021-03-04 09:39:26
 */
@RestController
@RequestMapping("/home")
public class HomeController {
    private final HomeService homeService;

    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    /**
     * 请求首页信息
     * @param request
     * @return
     */
    @PostMapping("/homeInitInfo")
    public Response<HomeInitInfoRes> homeInitInfo(@Valid @RequestBody Request<HomeInitInfoReq> request) {
        return homeService.homeInitInfo(request);
    }
}

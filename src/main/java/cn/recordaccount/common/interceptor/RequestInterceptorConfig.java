package cn.recordaccount.common.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * 拦截器总配置类
 * @author rollin
 * @date 2021-03-03 16:59:10
 */
@Configuration
public class RequestInterceptorConfig extends WebMvcConfigurationSupport {
    /**
     * 所有
     */
    private static final String ALL = "/**";
    private static final String LOGIN = "/user/login";
    private static final String REGISTER = "/user/register";
    private static final String QUERY_ALL_CLASSIFY = "/record/queryClassify";

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        /**
         * Jwt 拦截器
         */
        List<String> ignoreJwtList = new ArrayList<>();
        ignoreJwtList.add(LOGIN);
        ignoreJwtList.add(REGISTER);
        //因为查询图表在不登录的情况也可以请求所以此处应该 略过jwt 验证
        ignoreJwtList.add(QUERY_ALL_CLASSIFY);
        registry.addInterceptor(new JwtInterceptor())
                //拦截所有请求
                .addPathPatterns(ALL)
                // 忽略请求
                .excludePathPatterns(ignoreJwtList);


        super.addInterceptors(registry);
    }
}

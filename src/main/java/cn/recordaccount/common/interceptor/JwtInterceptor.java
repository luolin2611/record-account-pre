package cn.recordaccount.common.interceptor;

import cn.recordaccount.common.constant.ResStatusEnum;
import cn.recordaccount.common.dto.Response;
import cn.recordaccount.common.util.JwtUtils;
import cn.recordaccount.common.util.RSAUtil;
import cn.recordaccount.common.util.YamlUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * jwt 签名认证
 * @author rollin
 * @date 2021-03-03 17:14:45
 */
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        // 验证jwt token
        String token = request.getHeader("Authorization");
        Response result;
        if(token == null || token.equals("")) {
            // token 为空
            result = Response.error(ResStatusEnum.JWT_TOKEN_IS_NULL);
            response.getWriter().write(JSON.toJSONString(result));
            return false;
        }

        result = JwtUtils.vaildateJwt(token, RSAUtil.getPublicKey(YamlUtil.getValue("jwt.public-key")));
        String checkState = result.getCode();
        if(!checkState.equals(ResStatusEnum.SUCCESS.getCode())) {
            //验签失败
            response.getWriter().write(JSON.toJSONString(result));
            return false;
        }
        return true;
    }
}

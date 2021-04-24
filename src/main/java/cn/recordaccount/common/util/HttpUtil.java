package cn.recordaccount.common.util;

import cn.recordaccount.common.constant.ResStatusEnum;
import cn.recordaccount.common.dto.Request;
import cn.recordaccount.common.dto.Response;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletRequest;
import java.io.*;
import java.util.Map;

/**
 * Http 请求工具类
 *
 * @author rollin
 * @date 2021-02-24 17:31:51
 */
@Slf4j
@Component
public class HttpUtil {
    public static String getBodyString(ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        InputStream is = null;
        BufferedReader reader = null;
        try {
            is = request.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    private static RestTemplate setRequestTimeout(RestTemplate restTemplate) {
        SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        //单位为ms 建立超时时间
        clientHttpRequestFactory.setConnectTimeout(Integer.valueOf(YamlUtil.getValue("rest.connection.connect-timeout")));
        //单位为ms 建立连接成功后 从服务器读取超时
        clientHttpRequestFactory.setReadTimeout(Integer.valueOf(YamlUtil.getValue("rest.connection.read-timeout")));
        restTemplate.setRequestFactory(clientHttpRequestFactory);
        return restTemplate;
    }

    /**
     * 请求后台
     *
     * @param request
     * @return
     */
    public static Response getResponse(Request request, String serveName, String requestMethod) {
        //组装系统报文头

        //全局流水号
        Map result = HttpUtil.httpClientPost(request, serveName, requestMethod);
        String json = JSON.toJSONString(result);
        JSONObject jsonObject = JSON.parseObject(json, JSONObject.class);
        if (jsonObject != null) {
            String stateCode = jsonObject.getString("code");
            if (ResStatusEnum.SUCCESS.getCode().equals(stateCode)) {
                JSONObject returnBody = jsonObject.getJSONObject("body");
                return Response.success(jsonObject.getString("msg"), returnBody);
            } else {
                return Response.error(stateCode, jsonObject.getString("msg"));
            }
        }
        return Response.error();
    }

    /**
     * 发送请求 (返回Map)
     *
     * @param request
     * @param serveName     服务名称： 例如 user 、record-account
     * @param requestMethod 例如：
     * @return
     */
    public static Map httpClientPost(Request request, String serveName, String requestMethod) {
        StringBuilder requestUrl = new StringBuilder("http://").append(YamlUtil.getValue("remote.ip"))
                .append(":").append(YamlUtil.getValue("remote." + serveName + "-port")).append("/" + serveName).append(requestMethod);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestJson = JSON.toJSONString(request);
        log.info("请求报文（{}）：\r\n {}", requestMethod, FormatUtil.formatJson(requestJson));

        org.springframework.http.HttpEntity<String> entity = new org.springframework.http.HttpEntity<>(requestJson, headers);
        long startTime = System.currentTimeMillis();
        RestTemplate restTemplate = new RestTemplate();
        //设置超时时间
        setRequestTimeout(restTemplate);
        log.info("请求url：{}", requestUrl);
        ResponseEntity responseEntity = restTemplate.postForEntity(requestUrl.toString(), entity, Map.class);
        log.info("响应报文（{}）：\r\n {}", requestMethod, FormatUtil.formatJson(JSON.toJSONString(responseEntity)));

        long requestTime = System.currentTimeMillis() - startTime;
        String exceededStr = "";
        if (requestTime > 3000) {
            exceededStr = " | request time exceeded 3000ms";
        }
        log.info("请求接口（" + requestMethod + "）->>> " + requestMethod + " <<<- 耗时：" + requestTime + "ms" + exceededStr);
        return (Map) (responseEntity.getBody());
    }
}

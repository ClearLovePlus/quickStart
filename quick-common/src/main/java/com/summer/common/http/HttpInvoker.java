package com.summer.common.http;

import com.alibaba.fastjson.JSON;
import com.summer.common.constants.DapConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

/**
 * @description: 远程调用父类
 * @author: chenhao
 * @date: 2023-7-20 9:14
 */
@Slf4j
public class HttpInvoker {
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 对线头是application/json的请求
     *
     * @param url
     * @param parameter
     * @param method
     * @return
     */
    public String invokeJson(String url, Object parameter, HttpHeaders headers, HttpMethod method) {
        headers.setContentType(MediaType.APPLICATION_JSON);
        return invoke(url, parameter, headers, method);

    }

    /**
     * form表单的请求模式
     *
     * @param url
     * @param parameter
     * @param headers
     * @param method
     * @return
     */
    public String invokeForm(String url, Object parameter, HttpHeaders headers, HttpMethod method) {
        //todo
        return "";
    }

    /**
     * 普通其他通用请求模式
     *
     * @param url
     * @param parameter
     * @param headers
     * @param method
     * @return
     */
    public String invoke(String url, Object parameter, HttpHeaders headers, HttpMethod method) {
        String result = StringUtils.EMPTY;
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try {
            log.info("调用的接口是:[{}],参数是:[{}],请求头是:[{}],请求方式是:[{}]", url, JSON.toJSONString(parameter), headers.toString(), method.toString());
            HttpEntity<Object> entity = new HttpEntity<>(parameter, headers);
            ResponseEntity<String> resp = restTemplate.exchange(url, method, entity, String.class);
            stopWatch.stop();
            if (DapConstants.HTTP_SUCCESS.equals(resp.getStatusCodeValue())) {
                result = resp.getBody();
                log.info("调用的接口是:[{}],参数是:[{}],请求头是:[{}],请求方式是:[{}],返回结果:[{}],耗时：{}ms", url, JSON.toJSONString(parameter), headers.toString(), method.toString(), result, stopWatch.getTotalTimeMillis());
            }
        } catch (Exception e) {
            log.info("接口异常！调用的接口是:[{}],参数是:[{}],请求头是:[{}],请求方式是:[{}],返回结果:[{}],耗时：{}ms", url, JSON.toJSONString(parameter), headers.toString(), method.toString(), result, stopWatch.getTotalTimeMillis(), e);
        }
        return result;
    }
}

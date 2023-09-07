package com.summer.common.util;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.dingtalk.api.response.OapiMessageCorpconversationAsyncsendV2Response;
import com.summer.common.config.DingConfig;
import com.summer.common.constants.DapConstants;
import com.taobao.api.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * DingDingHandler
 * Created on 2023-7-7
 * Copyright 2021 Tebon Financial. All  Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * <p>
 * Please contact Fosun Corporation or visit
 * www.tebon.com.cn
 * if you need additional information or have any questions.
 *
 * @author hify
 * @version 1.0
 */
@Component
@Slf4j
public class DingUtils{

    public static boolean sendMessage(String dingNo,String content,String title){
        try {
            if (StringUtils.isBlank(dingNo)){
                log.error("没有录入钉钉号,{}",dingNo);
                return true;
            }
            DingConfig dingConfig = SpringUtils.getBean(DingConfig.class);
            DingTalkClient client = new DefaultDingTalkClient(dingConfig.getApiUrl()+ DapConstants.DING_SEND_URL);
            OapiMessageCorpconversationAsyncsendV2Request request = new OapiMessageCorpconversationAsyncsendV2Request();
            request.setAgentId(dingConfig.getAgentId());
            request.setUseridList(dingNo);
            request.setToAllUser(false);
            OapiMessageCorpconversationAsyncsendV2Request.Markdown markdown = new OapiMessageCorpconversationAsyncsendV2Request.Markdown();
            markdown.setText(content);
            markdown.setTitle(title+ new Date());
            OapiMessageCorpconversationAsyncsendV2Request.Msg msg = new OapiMessageCorpconversationAsyncsendV2Request.Msg();
            msg.setMsgtype("markdown");
            msg.setMarkdown(markdown);
            request.setMsg(msg);
            OapiMessageCorpconversationAsyncsendV2Response rsp = client.execute(request, getDingToken(dingConfig.getAppKey(),dingConfig.getAppSecret()));
            if (!rsp.isSuccess()){
                rsp = client.execute(request, refreshDingToken(dingConfig.getAppKey(),dingConfig.getAppSecret()));
            }
            return rsp.isSuccess();
        }catch (Exception e){
            log.error("发送钉钉失败，userName={},msg={},e={}",dingNo,content,e);
        }
        return false;
    }


    /**
     * 刷新token
     * @return
     */
    private static String refreshDingToken(String appKey,String appSecret){
        DingConfig dingConfig = SpringUtils.getBean(DingConfig.class);
        DefaultDingTalkClient client = new DefaultDingTalkClient(dingConfig.getApiUrl()+DapConstants.DING_TOKEN_URL);
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setAppkey(appKey);
        request.setAppsecret(appSecret);
        request.setHttpMethod(DapConstants.GET);
        try {
            OapiGettokenResponse response = client.execute(request);
            if (response != null && response.isSuccess()){
                String accessToken = response.getAccessToken();
                log.info("刷新获得的钉钉token={}，appKey={}",accessToken,appKey);
                return accessToken;
            }
        } catch (ApiException e) {
            log.error("get dingding token failed, e= {}",e);
        }
        return null;
    }


    /**
     * 获取钉钉token
     * @return
     */
    private static String getDingToken(String appKey,String appSecret){
        StringRedisTemplate stringRedisTemplate = SpringUtils.getBean(StringRedisTemplate.class);
        if (stringRedisTemplate == null){
            return refreshDingToken(appKey,appSecret);
        }
        String token = stringRedisTemplate.opsForValue().get(DapConstants.DING_DING_REDIS_KEY + appKey);
        if (StringUtils.isNotBlank(token)){
            return token;
        }
        return refreshDingToken(appKey,appSecret);
    }
}

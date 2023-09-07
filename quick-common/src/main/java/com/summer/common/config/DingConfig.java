package com.summer.common.config;

import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * DingConfig
 * Created on 2023-8-16
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
@Primary
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "ding")
public class DingConfig {

    private String apiUrl;

    private String appKey;

    private String appSecret;

    private Long agentId;

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }
}

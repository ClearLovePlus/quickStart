package com.quick.api.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: chenhao
 * @date: 2023-7-24 14:47
 */
@Component
@RefreshScope
@Data
public class DapPushConfig {
    @Value("${dap.error.url}")
    private String dapErrorUrl;
    @Value("${dap.redirect.account.url}")
    private String dapRedirectAccountUrl;
    @Value("${dap.redirect.phone.url}")
    private String dapRedirectPhoneUrl;
    @Value("${dap.ignore.code}")
    private String dapIgnoreCode;
}

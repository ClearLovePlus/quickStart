package com.quick.api.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @description: magic-api配置
 * @author: chenhao
 * @date: 2023-7-20 13:30
 */
@RefreshScope
@Data
@Component
public class MagicConfig {
    @Value("${magic.url}")
    private String magicUrl;
    @Value("${magic.token}")
    private String magicToken;
}

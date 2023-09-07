package com.summer.common.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Primary;

/**
 * @description:
 * @author: chenhao
 * @date: 2023-9-7 15:08
 */
@Data
@Primary
@NoArgsConstructor
@EnableConfigurationProperties(KafKaConfig.class)
@ConfigurationProperties(prefix = "kafka")
public class KafKaConfig {
    private String address;
    private String groupId;
}

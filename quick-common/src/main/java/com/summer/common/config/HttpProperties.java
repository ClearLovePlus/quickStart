package com.summer.common.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Primary;

import java.time.Duration;

/**
 * @author wangjunjun
 */
@Data
@Primary
@NoArgsConstructor
@EnableConfigurationProperties(HttpProperties.class)
@ConfigurationProperties(prefix = "http")
public class HttpProperties {
    private Duration connectTimeout;
    private Duration readTimeout;
    private Duration connectionRequestTimeout;
    private Integer maxConnTotal;
    private Integer maxConnPerRoute;
}

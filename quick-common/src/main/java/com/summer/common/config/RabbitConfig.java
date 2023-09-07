package com.summer.common.config;

import com.summer.common.exceptions.DapException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerErrorHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @description: rabbit mq配资
 * @author: chenhao
 * @date: 2023-9-7 13:07
 */
@Slf4j
@Configuration
public class RabbitConfig {
    public static final long REPLY_TIMEOUT=10000L;
    public static final String KEY_ERROR = "error";
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean("parsingErrorHandler")
    public RabbitListenerErrorHandler handleParsingError() {
        return (amqpMessage, message, exception) -> {
            if (exception.getCause() instanceof DapException) {
                DapException e = (DapException) exception.getCause();
                log.error(e.getMessage());
                return Map.of(KEY_ERROR, exception.getCause().getMessage());
            }
            return Map.of(KEY_ERROR, exception.getMessage());
        };
    }

    @Bean
    @ConditionalOnMissingBean(RabbitTemplate.class)
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        rabbitTemplate.setReplyTimeout(REPLY_TIMEOUT);
        return rabbitTemplate;
    }
}

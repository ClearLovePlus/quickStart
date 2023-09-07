package com.summer.common.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.nio.charset.StandardCharsets;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dongzhuming
 */
@Configuration
@RequiredArgsConstructor
@Slf4j
@Import(HttpProperties.class)
public class HttpConfig {

    private final HttpProperties httpProperties;

    @Bean
    public ClientHttpRequestFactory simpleSslClientHttpRequestFactory() throws Exception {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout((int) httpProperties.getConnectTimeout().toMillis() + 7);
        factory.setReadTimeout((int) httpProperties.getReadTimeout().toMillis());
        factory.setBufferRequestBody(false);
        factory.setConnectionRequestTimeout((int) httpProperties.getConnectionRequestTimeout().toMillis());
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClientBuilder.setMaxConnTotal(httpProperties.getMaxConnTotal());
        httpClientBuilder.setMaxConnPerRoute(httpProperties.getMaxConnPerRoute());
        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                .loadTrustMaterial(null, (X509Certificate[] chain, String authType) -> true)
                .build();
        httpClientBuilder.setSSLContext(sslContext);
        httpClientBuilder.setSSLHostnameVerifier((requestedHost, remoteServerSession) -> requestedHost.equalsIgnoreCase(remoteServerSession.getPeerHost()));
        factory.setHttpClient(httpClientBuilder.build());
        return factory;
    }


    @Bean("sslRestTemplate")
    public RestTemplate sslRestTemplate(ClientHttpRequestFactory simpleSslClientHttpRequestFactory) {
        return getRestTemplate(simpleSslClientHttpRequestFactory);
    }

    private RestTemplate getRestTemplate(ClientHttpRequestFactory simpleClientHttpRequestFactory) {
        RestTemplate restTemplate = new RestTemplate(simpleClientHttpRequestFactory);
        List<HttpMessageConverter<?>> newConverterList = new ArrayList<>();
        restTemplate.getMessageConverters().forEach(converter -> {
            if (converter.getClass() == StringHttpMessageConverter.class) {
                newConverterList.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
            } else {
                newConverterList.add(converter);
            }
        });
        restTemplate.setMessageConverters(newConverterList);
        return restTemplate;
    }


}

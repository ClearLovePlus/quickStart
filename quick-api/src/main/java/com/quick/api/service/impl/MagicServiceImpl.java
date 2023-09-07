package com.quick.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.quick.api.config.MagicConfig;
import com.topsperity.common.constants.DapConstants;
import com.topsperity.common.dto.Resp;
import com.topsperity.common.http.HttpInvoker;
import com.quick.api.dto.DiffTypeDetailResponseDTO;
import com.quick.api.service.IMagicService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description:
 * @author: chenhao
 * @date: 2023-7-20 13:27
 */
@Component
public class MagicServiceImpl extends HttpInvoker implements IMagicService {
    @Autowired
    private MagicConfig magicConfig;

    @Override
    public Resp<List<DiffTypeDetailResponseDTO>> getDiffTypeDetail(String url) {
        Resp<List<DiffTypeDetailResponseDTO>> resp = new Resp<>();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(DapConstants.MAGIC_AUTH_TOKEN, magicConfig.getMagicToken());
        String result = invokeJson(url, null, httpHeaders, HttpMethod.GET);
        if (StringUtils.isNotBlank(result)) {
            resp = JSON.parseObject(result, new TypeReference<>() {
            });
            return resp;
        }
        return Resp.failed();
    }
}

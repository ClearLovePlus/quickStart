package com.quick.api.web;

import com.quick.api.annotations.PermissionAuth;
import com.quick.api.dto.DiffTypeDetailResponseDTO;
import com.quick.api.dto.OaUser;
import com.quick.api.dto.PushContentResponseDTO;
import com.quick.api.service.IPushTypeService;
import com.quick.api.service.IPushWhiteListService;
import com.topsperity.common.dto.Resp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @description: 推送接口
 * @author: chenhao
 * @date: 2023-7-20 9:32
 */
@RestController
@RequestMapping("/push")
@Api(tags = "推送服务")
@RequiredArgsConstructor
@Slf4j
public class PushController {
    private final IPushTypeService pushTypeService;
    private final IPushWhiteListService pushWhiteListService;

    @ApiOperation("查询数据对比信息")
    @RequestMapping(value = "/getDiff", method = RequestMethod.GET)
    @PermissionAuth
    @CrossOrigin(methods = RequestMethod.GET)
    Resp<PushContentResponseDTO<DiffTypeDetailResponseDTO>> getDiffDetail(
            @RequestParam("typeCode") String typeCode,
            @RequestParam(value = "dicValue", required = false) String dicValue,
            @RequestParam(value = "oaAccount", required = false) String oaAccount,
            @RequestParam(value = "phoneNum", required = false) String phoneNum) {
        log.info("请求参数:typeCode:{},dicValue:{},oaAccount:{},phoneNum:{}",typeCode,dicValue,oaAccount,phoneNum);
        return pushTypeService.getDiffTypeDetailResponse(typeCode, dicValue);
    }

    @ApiOperation("查询数据对比信息")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @CrossOrigin(methods = RequestMethod.GET)
    public void login(HttpServletResponse  response,
                      @RequestParam(name = "oa_user", required = true) String oaUser,
                      @RequestParam(name = "oa_sign", required = false) String oaSign,
                      @RequestParam(name = "typeCode", required = false) String typeCode) {

        log.info("自动登录用户{},oa_sign:{},typeCode,{}",oaUser,oaSign,typeCode);
        OaUser login = pushWhiteListService.login(oaUser, typeCode);
        try {
            log.info("跳转地址为:{}",login.getRedirect());
            response.sendRedirect(login.getRedirect());
        }catch (Exception e){
            log.error("跳转异常用户:{},推送类型:{}",oaSign,typeCode);
        }

    }
}

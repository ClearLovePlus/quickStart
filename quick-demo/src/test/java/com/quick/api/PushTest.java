package com.quick.api;

import com.quick.api.common.ReturnCodeEnum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.CoreMatchers.is;

/**
 * @description:
 * @author: chenhao
 * @date: 2023-7-20 13:57
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = QuickApplication.class)
public class PushTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testPushWithOutTypeCode() throws Exception{
        RequestBuilder request;
        request=get("/push/getDiff")
                .param("typeCode","")
                .param("dicValue","");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code",is(ReturnCodeEnum.PUSH_AUTH_ERROR.getCode())))
                .andDo(print());
    }

    @Test
    public void testPushWithOutPhoneAndOaAccount() throws Exception{
        RequestBuilder request;
        request=get("/push/getDiff")
                .param("typeCode","datacompare")
                .param("dicValue","流通股本不同")
                .param("oaAccount","")
                .param("phone","");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code",is(ReturnCodeEnum.PUSH_AUTH_MOBILE_OA_ERROR.getCode())))
                .andDo(print());
    }

    @Test
    public void testPushWithOaAccount() throws Exception{
        RequestBuilder request;
        request=get("/push/getDiff")
                .param("typeCode","datacompare")
                .param("dicValue","流通股本不同")
                .param("oaAccount","chenhao3")
                .param("phone","");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code",is(0)))
                .andDo(print());
    }

    @Test
    public void testPushWithPhone() throws Exception{
        RequestBuilder request;
        request=get("/push/getDiff")
                .param("typeCode","datacompare")
                .param("dicValue","")
                .param("oaAccount","chenhao3")
                .param("phoneNum","18616578042");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code",is(0)))
                .andDo(print());
    }

}

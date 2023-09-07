package com.quick.api.dto;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @description:
 * @author: chenhao
 * @date: 2023-7-24 14:38
 */
public class OaUser {
    private String user;
    private String userName;
    private String jobCodeth;
    private String jobTel;
    private String redirect;
    private String dd_id;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserName() {
        if(StringUtils.isNotEmpty(userName)){
            return URLEncoder.encode(userName, StandardCharsets.UTF_8);
        }

        return "";
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getJobCodeth() {
        return jobCodeth;
    }

    public void setJobCodeth(String jobCodeth) {
        this.jobCodeth = jobCodeth;
    }

    public String getJobTel() {
        return jobTel;
    }

    public void setJobTel(String jobTel) {
        this.jobTel = jobTel;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public String getDd_id() {
        return dd_id;
    }

    public void setDd_id(String dd_id) {
        this.dd_id = dd_id;
    }
}

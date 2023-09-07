package com.summer.common.constants;

/**
 * @description:
 * @author: chenhao
 * @date: 2023-7-20 10:41
 */
public class DapConstants {
    /**
     * 成功
     */
    public static Integer SUCCESS=0;
    /**
     * 成功名称
     */
    public static String  SUCCESS_DESC="success";
    /**
     * 失败 其他的失败类型用枚举标识
     */
    public static Integer FAILED=1;

    /**
     * 失败标识
     */
    public static String FAILED_DESC="failed";

    /**
     * http调用成功
     */
    public static Integer HTTP_SUCCESS=200;

    /**
     * magic-api调用需要带的请求头
     */
    public static String MAGIC_AUTH_TOKEN="M-AUTH-TOKEN";

    /**
     * 已删除
     */
    public static Integer IS_DELETED=1;
    /**
     * 未删除
     */
    public static Integer NOT_DELETED=0;


    /**
     * 钉钉
     */
    public final static String DING_SEND_URL = "/topapi/message/corpconversation/asyncsend_v2";
    public final static String DING_TOKEN_URL = "/gettoken";

    /**
     * http请求方式
     */
    public final static String GET = "GET";
    public final static String POST = "POST";

    /**
     * redis
     */
    public final static String DING_DING_REDIS_KEY="DATA_ASSET_MANAGE:DING_DING_REDIS_KEY:";
}

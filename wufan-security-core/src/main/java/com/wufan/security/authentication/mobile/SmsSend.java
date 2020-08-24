package com.wufan.security.authentication.mobile;

/**
 * 短信发送统一接口
 * @author wufan
 * @date 2020/4/11 0011 2:37
 */
public interface SmsSend {

    /**
     * 发送短信
     * @param mobile 手机号
     * @param content 发送的内容
     * @return true 表示发送成功，false发送失败
     */
    boolean sendSms(String mobile, String content);
}

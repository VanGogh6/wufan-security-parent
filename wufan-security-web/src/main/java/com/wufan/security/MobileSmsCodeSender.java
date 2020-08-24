package com.wufan.security;

import com.wufan.security.authentication.mobile.SmsSend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wufan
 * @date 2020/4/11 0011 2:37
 */
//@Component
public class MobileSmsCodeSender implements SmsSend {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean sendSms(String mobile, String content) {
        logger.info("Web应用新的短信验证码接口---向手机号"+mobile+"发送了验证码为：" + content);
        return false;
    }
}

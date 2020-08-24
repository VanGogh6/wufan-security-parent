package com.wufan.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * 加载认证信息配置类
 * @author wufan
 * @date 2020/4/11 0011 2:37
 */
@Configuration
public class ReloadMessageConfig {

    @Bean // 加载中文的认证提示信息
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        //.properties 不要加到后面
//        messageSource.setBasename("classpath:org/springframework/security/messages_zh_CN");
        messageSource.setBasename("classpath:messages_zh_CN");
        return messageSource;
    }
}

package com.wufan.security.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * 授权配置统一接口，所有模块的授权配置类都要实现这个接口
 * @author wufan
 * @date 2020/4/11 0011 2:37
 */
public interface AuthorizeConfigurerProvider {

    void confiure(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config);

}

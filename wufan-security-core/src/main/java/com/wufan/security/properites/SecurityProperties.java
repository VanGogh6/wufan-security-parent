package com.wufan.security.properites;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wufan
 * @date 2020/4/11 0011 2:37
 */
@Component // 不要少了
@ConfigurationProperties(prefix = "mengxuegu.security")
public class SecurityProperties {

    // 会将 mengxuegu.security.authentication 下面的值绑定到AuthenticationProperties对象上
    private AuthenticationProperties authentication;

    public AuthenticationProperties getAuthentication() {
        return authentication;
    }

    public void setAuthentication(AuthenticationProperties authentication) {
        this.authentication = authentication;
    }
}

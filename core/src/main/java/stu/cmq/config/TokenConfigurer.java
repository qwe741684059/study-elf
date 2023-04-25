package stu.cmq.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import stu.cmq.filter.TokenFilter;
import stu.cmq.properties.SecurityProperties;
import stu.cmq.service.OnlineUserService;
import stu.cmq.utils.TokenProvider;
import stu.cmq.utils.UserCacheManager;

import javax.swing.*;

/**
 * @author kamifeng
 * @date 9:22
 */

@RequiredArgsConstructor
public class TokenConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final TokenProvider tokenProvider;
    private final SecurityProperties properties;
    private final OnlineUserService onlineUserService;
    private final UserCacheManager userCacheManager;

    @Override
    public void configure(HttpSecurity http) {
        TokenFilter customFilter = new TokenFilter(tokenProvider, properties, onlineUserService, userCacheManager);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}

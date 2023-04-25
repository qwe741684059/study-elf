package stu.cmq.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import stu.cmq.properties.LoginProperties;
import stu.cmq.properties.SecurityProperties;

/**
 * @apiNote 配置Bean
 * @author kamifeng
 * @date 21:20
 */

@Configuration
public class BeanConfig {

    @Bean
    @ConfigurationProperties(prefix = "jwt")
    public SecurityProperties securityProperties() {
        return new SecurityProperties();
    }
    @Bean
    @ConfigurationProperties(prefix = "login")
    public LoginProperties loginProperties() {
        return new LoginProperties();
    }
}

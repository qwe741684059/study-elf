package stu.cmq.annotation.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author kamifeng
 * @date 10:33
 */

@Configuration
@ConfigurationProperties(prefix = "file")
@Data
public class FileProperties {

    private String path;

    private String avatar;

    /** 文件大小限制 */
    private Long maxSize;

    /** 头像大小限制 */
    private Long avatarMaxSize;
}

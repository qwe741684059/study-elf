package stu.cmq.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author kamifeng
 * @date 23:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnlineUserDTO implements Serializable {

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * Token令牌
     */
    private String token;

    /**
     * 登录时间
     */
    private LocalDateTime loginTime;
}

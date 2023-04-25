package stu.cmq.properties;

import lombok.Data;

/**
 * @author kamifeng
 * @date 8:19
 */

@Data
public class LoginProperties {

    /**
     * 账号单用户 登录
     */
    private boolean singleLogin = false;

    public static final String CACHE_KEY = "USER-LOGIN-DATA";

    public boolean isSingleLogin() {
        return singleLogin;
    }
}

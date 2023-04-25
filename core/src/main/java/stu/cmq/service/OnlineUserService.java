package stu.cmq.service;

import stu.cmq.service.dto.JwtUserDTO;
import stu.cmq.service.dto.OnlineUserDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author kamifeng
 * @date 23:39
 */

public interface OnlineUserService {

    /**
     * 保存在线用户信息
     * @param jwtUserDTO
     * @param token
     */
    void save(JwtUserDTO jwtUserDTO, String token);

    /**
     * 检测用户是否已登录，已登录则踢下线
     * @param userName
     * @param igoreToken
     */
    void checkLoginOnUser(String userName, String igoreToken);

    /**
     * 查询用户
     * @param key /
     * @return /
     */
    OnlineUserDTO getOne(String key);

    /**
     * 退出登录
     * @param token /
     */
    void logout(String token);

}

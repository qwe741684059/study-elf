package stu.cmq.service;

import stu.cmq.domain.User;
import stu.cmq.service.dto.AuthorityDTO;

import java.util.List;

/**
 * @author kamifeng
 * @date 12:34
 */

public interface RoleService {

    /**
     * 获取用户权限信息
     * @param user 用户信息
     * @return 权限信息
     */
    List<AuthorityDTO> mapToGrantedAuthorities(User user);
}

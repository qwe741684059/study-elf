package stu.cmq.service;

import stu.cmq.domain.UserRole;

/**
 * @author kamifeng
 * @date 15:02
 */

public interface UserRoleService {
    /**
     * 新增用户角色关系
     * @param userRole
     */
    void insertUserRole(UserRole userRole);
}

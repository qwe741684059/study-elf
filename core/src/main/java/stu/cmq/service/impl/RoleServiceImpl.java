package stu.cmq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stu.cmq.domain.Role;
import stu.cmq.domain.User;
import stu.cmq.mapper.RoleMapper;
import stu.cmq.service.RoleService;
import stu.cmq.service.dto.AuthorityDTO;
import stu.cmq.utils.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author kamifeng
 * @date 12:36
 */

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<AuthorityDTO> mapToGrantedAuthorities(User user) {
        Set<String> permissions = new HashSet<>();
        Set<Role> roles = roleMapper.findByUserId(user.getUserId());
        permissions = roles.stream()
                .map(Role::getRoleName)
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.toSet());
        return permissions.stream().map(AuthorityDTO::new).collect(Collectors.toList());

    }
}

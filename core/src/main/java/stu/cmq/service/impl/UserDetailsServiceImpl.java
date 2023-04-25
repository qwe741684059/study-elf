package stu.cmq.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import stu.cmq.domain.User;
import stu.cmq.exception.EntityNotFoundException;
import stu.cmq.service.RoleService;
import stu.cmq.service.UserService;
import stu.cmq.service.dto.JwtUserDTO;
import stu.cmq.utils.UserCacheManager;

/**
 * @author kamifeng
 * @date 10:46
 */

@Slf4j
@RequiredArgsConstructor
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;
    private final RoleService roleService;
    private final UserCacheManager userCacheManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        JwtUserDTO jwtUserDTO = userCacheManager.getUserCache(username);
        if (jwtUserDTO == null) {
            User user;
            try {
                user = userService.selectByUsername(username);
            } catch (EntityNotFoundException e) {
                throw new UsernameNotFoundException(username, e);
            }
            if (user == null) {
                throw new UsernameNotFoundException("");
            } else {
                jwtUserDTO = new JwtUserDTO(
                        user,
                        roleService.mapToGrantedAuthorities(user)
                );
                userCacheManager.addUserCache(username, jwtUserDTO);
            }
        }
        return jwtUserDTO;
    }
}

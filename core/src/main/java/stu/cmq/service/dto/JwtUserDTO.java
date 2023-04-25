package stu.cmq.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import stu.cmq.domain.User;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author kamifeng
 * @date 22:08
 */

@Data
@AllArgsConstructor
public class JwtUserDTO implements UserDetails {

    private final User user;


    private final List<AuthorityDTO> authorities;

    public Set<String> getRoles() {
        return authorities.stream().map(AuthorityDTO::getAuthority).collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

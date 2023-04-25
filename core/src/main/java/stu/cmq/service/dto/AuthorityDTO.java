package stu.cmq.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author kamifeng
 * @date 22:57
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorityDTO implements GrantedAuthority {
    private String authority;
}

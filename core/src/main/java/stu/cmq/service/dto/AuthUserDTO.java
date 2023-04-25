package stu.cmq.service.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author kamifeng
 * @date 20:38
 */

@Data
public class AuthUserDTO {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}

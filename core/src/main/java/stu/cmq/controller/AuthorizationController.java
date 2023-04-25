package stu.cmq.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stu.cmq.annotation.rest.AnonymousDeleteMapping;
import stu.cmq.annotation.rest.AnonymousPostMapping;
import stu.cmq.properties.LoginProperties;
import stu.cmq.properties.SecurityProperties;
import stu.cmq.service.OnlineUserService;
import stu.cmq.service.dto.AuthUserDTO;
import stu.cmq.service.dto.JwtUserDTO;
import stu.cmq.utils.SecurityUtils;
import stu.cmq.utils.TokenProvider;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kamifeng
 * @date 14:02
 */

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Api(tags = "用户接口管理")
public class AuthorizationController {

    private final SecurityProperties properties;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;
    private final OnlineUserService onlineUserService;

    @Resource
    private LoginProperties loginProperties;

    @ApiOperation("登录授权")
    @AnonymousPostMapping(value = "/login")
    public ResponseEntity<Object> login(@Validated @RequestBody AuthUserDTO authUserDTO) {

        // 鉴权
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(authUserDTO.getUsername(), authUserDTO.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 根据鉴权信息获取token
        String token = tokenProvider.createToken(authentication);
        final JwtUserDTO jwtUserDTO = (JwtUserDTO) authentication.getPrincipal();

        //保存在线信息
        onlineUserService.save(jwtUserDTO, token);

        //返回token与用户信息
        Map<String, Object> authInfo = new HashMap<String, Object>(2){{
            put("token", properties.getTokenStartWith() + token);
            put("user", jwtUserDTO);
        }};
        if (loginProperties.isSingleLogin()) {
            //踢掉之前已经登录的token
            onlineUserService.checkLoginOnUser(authUserDTO.getUsername(), token);
        }
        return new ResponseEntity<>(authInfo, HttpStatus.OK);
    }
    @ApiOperation("获取用户信息")
    @GetMapping(value = "/info")
    public ResponseEntity<Object> getUserInfo() {
        return ResponseEntity.ok(SecurityUtils.getCurrentUser());
    }

    @ApiOperation("退出登录")
    @AnonymousDeleteMapping(value = "/logout")
    public ResponseEntity<Object> logout(HttpServletRequest request) {
        onlineUserService.logout(tokenProvider.getToken(request));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

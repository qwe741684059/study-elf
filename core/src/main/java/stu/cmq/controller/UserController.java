package stu.cmq.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import stu.cmq.annotation.rest.AnonymousPostMapping;
import stu.cmq.domain.User;
import stu.cmq.service.UserService;

/**
 * @author kamifeng
 * @date 10:15
 */

@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
public class UserController {

    @Autowired
    private UserService userService;
    @ApiOperation("用户注册")
    @AnonymousPostMapping("/insertUser")
    public ResponseEntity<Object> insertUser(@RequestBody User user) {
        userService.insertUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("修改头像")
    @PostMapping("updateAvatar")
    public ResponseEntity<Object> updateAvatar(@RequestParam(value = "file") MultipartFile multipartFile) {
        userService.updateAvatar(multipartFile);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("修改用户")
    @PostMapping("updateUser")
    public ResponseEntity<Object> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

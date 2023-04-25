package stu.cmq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import stu.cmq.annotation.properties.FileProperties;
import stu.cmq.domain.User;
import stu.cmq.domain.UserRole;
import stu.cmq.exception.BadRequestException;
import stu.cmq.mapper.RoleMapper;
import stu.cmq.mapper.UserMapper;
import stu.cmq.mapper.UserRoleMapper;
import stu.cmq.service.UserService;
import stu.cmq.utils.FileUtil;
import stu.cmq.utils.SecurityUtils;
import stu.cmq.utils.StringUtils;
import stu.cmq.utils.UserCacheManager;

import javax.validation.constraints.NotBlank;
import java.io.File;
import java.util.Objects;

/**
 * @author kamifeng
 * @date 10:12
 */

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleMapper userRoleMapper;
    private final FileProperties fileProperties;
    private final UserCacheManager userCacheManager;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.insert(user);
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getUserId());
        userRole.setRoleId(1L);
        userRoleMapper.insert(userRole);
    }

    @Override
    public User selectByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public void updateAvatar(MultipartFile multipartFile) {
        String baseUrl = "http://localhost:8181/avatar/";
        String image = "jpg png jpeg";
        // 文件大小验证
        // 验证文件上传的格式
        String fileType = FileUtil.getExtensionName(multipartFile.getOriginalFilename());
        if (fileType != null && !image.contains(fileType)){
            throw new BadRequestException("文件格式错误，仅支持" + image + "格式");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", SecurityUtils.getCurrentUsername());
        User user = userMapper.selectOne(queryWrapper);

        String oldPath = user.getAvatarPath();

        File file = FileUtil.upload(multipartFile, fileProperties.getAvatar());

        user.setAvatarPath(Objects.requireNonNull(file).getPath());
        user.setAvatar(baseUrl+file.getName());

        userMapper.updateById(user);
        if (StringUtils.isNotBlank(oldPath)) {
            FileUtil.del(oldPath);
        }
        @NotBlank String username = user.getUsername();
        flushCache(username);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateById(user);
        flushCache(user.getUsername());
    }

    public void flushCache(String username){
        userCacheManager.cleanUserCache(username);
    }
}

package stu.cmq.service;

import org.springframework.web.multipart.MultipartFile;
import stu.cmq.domain.Course;
import stu.cmq.domain.User;

/**
 * @author kamifeng
 * @date 10:11
 */

public interface UserService {

    /**
     * 创建用户
     * @param user
     */
    void insertUser(User user);

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User selectByUsername(String username);

    /**
     * 修改头像
     * @param multipartFile
     */
    void updateAvatar(MultipartFile multipartFile);

    /**
     * 修改用户
     * @param user
     */
    void updateUser(User user);


}

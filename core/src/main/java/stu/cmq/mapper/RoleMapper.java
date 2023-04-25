package stu.cmq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import stu.cmq.domain.Role;

import java.util.Set;

/**
 * @author kamifeng
 * @date 12:34
 */

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据用户id查询对应的角色
     * @param userId
     * @return
     */
    @Select("SELECT r.* From role r, user_role u WHERE r.role_id = u.role_id AND " +
            "u.user_id = #{userId}")
    Set<Role> findByUserId(Long userId);
}

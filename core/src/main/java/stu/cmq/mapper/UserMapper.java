package stu.cmq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import stu.cmq.domain.User;

/**
 * @author kamifeng
 * @date 10:11
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {
}

package stu.cmq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import stu.cmq.domain.Course;

/**
 * @author kamifeng
 * @date 8:44
 */

@Mapper
public interface CourseMapper extends BaseMapper<Course> {
}

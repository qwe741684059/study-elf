package stu.cmq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import stu.cmq.domain.Memorandum;

/**
 * @author kamifeng
 * @date 2023/4/24
 */

@Mapper
public interface MemorandumMapper extends BaseMapper<Memorandum> {
}

package stu.cmq.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import stu.cmq.base.BaseEntity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author kamifeng
 * @date 2023/4/24
 */

@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "memorandum")
public class Memorandum extends BaseEntity implements Serializable {
    @ApiModelProperty("备忘录id")
    @TableId(type = IdType.AUTO)
    private Long memorandumId;
    @ApiModelProperty("用户id")
    private Long userId;
    @ApiModelProperty("备忘内容")
    private String memorandumContent;
    @ApiModelProperty("备忘时间")
    private LocalDateTime memorandumTime;
}

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

/**
 * @author kamifeng
 * @date 8:40
 */

@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "course")
public class Course extends BaseEntity implements Serializable {

    @ApiModelProperty("课程Id")
    @TableId(type = IdType.AUTO)
    private Long courseId;
    @ApiModelProperty("用户Id")
    private Long userId;
    @ApiModelProperty("课程名称")
    private String courseName;
    @ApiModelProperty("课程星期几")
    private int courseDay;
    @ApiModelProperty("课程从第几节开始")
    private int courseStart;
    @ApiModelProperty("课程长度")
    private int courseLength;
}

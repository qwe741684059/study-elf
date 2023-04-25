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
 * @date 15:07
 */

@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "markdown")
public class Markdown extends BaseEntity implements Serializable {
    @ApiModelProperty("markdownid")
    @TableId(type = IdType.AUTO)
    private Long markdownId;
    @ApiModelProperty("用户id")
    private Long userId;
    @ApiModelProperty("名称")
    private String markdownName;
    @ApiModelProperty("内容")
    private String content;
    @ApiModelProperty("路径")
    private String markdownPath;
    @ApiModelProperty("类型(是文件夹还是markdown)")
    private String markdownType;
}

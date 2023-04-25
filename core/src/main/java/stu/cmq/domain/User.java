package stu.cmq.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import stu.cmq.base.BaseEntity;

import java.util.Set;

/**
 * @author kamifeng
 * @date 13:39
 */

@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "user")
public class User extends BaseEntity {

    @ApiModelProperty("用户Id")
    @TableId(type = IdType.AUTO)
    private Long userId;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("昵称")
    private String nickname;
    @ApiModelProperty("头像路径")
    private String avatar;
    @ApiModelProperty("头像本地路径")
    private String avatarPath;
}

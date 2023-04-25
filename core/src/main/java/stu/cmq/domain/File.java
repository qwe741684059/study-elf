package stu.cmq.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import stu.cmq.base.BaseEntity;

import java.io.Serializable;

/**
 * @author kamifeng
 * @date 22:06
 */

@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "file")
public class File extends BaseEntity implements Serializable {

    @ApiModelProperty("文件Id")
    @TableId(type = IdType.AUTO)
    private Long fileId;
    @ApiModelProperty("用户id")
    private Long userId;
    @ApiModelProperty("文件名")
    private String fileName;
    @ApiModelProperty("文件类型")
    private String fileType;
//    @ApiModelProperty("父节点")
//    private Long parentId;
    @ApiModelProperty("文件路径")
    private String filePath;
    @ApiModelProperty("服务器上文件存放路径")
    private String serverPath;
    @ApiModelProperty("预览文件用的路径")
    private String readPath;
}

package stu.cmq.service;

import com.alibaba.fastjson.JSONArray;
import org.springframework.web.multipart.MultipartFile;
import stu.cmq.domain.File;
import stu.cmq.domain.vo.FilePathVO;

import java.util.List;
import java.util.Map;

/**
 * @author kamifeng
 * @date 19:22
 */

public interface FileService {

    /**
     * 查找全部文件
     * @return List
     */
    List<File> selectAll();

    /**
     * 增加文件
     * @return int
     * @param file
     */
    int insertFile(File file);

    /**
     * 修改文件
     * @param file
     * @return int
     */
    int updateFile(File file);

    /**
     * 上传文件
     * @param multipartFile
     * @param filePath
     */
    void uploadFile(MultipartFile multipartFile, String filePath, String userId);

    /**
     * 删除文件
     * @param file
     */
    void deleteFile(File file);

//    /**
//     * 找到父亲的所有子节点
//     * @param parentId
//     * @return List
//     */
//    List<File> selectFileListByParentId(Long parentId);
//
//
//    /**
//     * 以树结构的形式找出所有文件
//     * @param fileId
//     * @return
//     */
//    JSONArray selectTree(Long fileId);

    /**
     * 根据文件路径寻找文件
     * @param filePathVO
     * @return
     */
    Map<String, List<File>> selectFileListByFilePath(FilePathVO filePathVO);

}

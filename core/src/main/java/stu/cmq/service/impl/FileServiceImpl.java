package stu.cmq.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import stu.cmq.annotation.properties.FileProperties;
import stu.cmq.domain.File;
import stu.cmq.domain.vo.FilePathVO;
import stu.cmq.exception.BadRequestException;
import stu.cmq.mapper.FileMapper;
import stu.cmq.service.FileService;
import stu.cmq.utils.FileUtil;
import stu.cmq.utils.SecurityUtils;

import java.util.*;

/**
 * @author kamifeng
 * @date 19:24
 */

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {


    private final FileMapper fileMapper;
    private final FileProperties fileProperties;

    @Override
    public List<File> selectAll() {
        return fileMapper.selectList(null);
    }

    @Override
    public int insertFile(File file) {
        return fileMapper.insert(file);
    }

    @Override
    public int updateFile(File file) {
        return fileMapper.updateById(file);
    }

    @Override
    public void uploadFile(MultipartFile multipartFile, String filePath, String userId) {
        String baseUrl = "http://192.168.31.113:8181/file/";
        String doc = "doc docx txt pdf";

        String fileType = FileUtil.getExtensionName(multipartFile.getOriginalFilename());
        if(fileType != null && !doc.contains(fileType)) {
            throw new BadRequestException("文件格式错误，仅支持" + doc + "格式");
        }

        java.io.File ioFile = FileUtil.upload(multipartFile, fileProperties.getPath());

        File file = new File();
        file.setFileName(FileUtil.getFileNameNoEx(multipartFile.getOriginalFilename()));
        file.setFileType(fileType);
        file.setFilePath(filePath);
        file.setUserId(Long.parseLong(userId));
        file.setServerPath(Objects.requireNonNull(ioFile).getPath());
        file.setReadPath(baseUrl+ ioFile.getName());

        QueryWrapper<File> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("file_name",file.getFileName());
        queryWrapper.eq("file_type",file.getFileType());
        queryWrapper.eq("file_path",file.getFilePath());
        queryWrapper.eq("user_id",file.getUserId());
        File file1 = fileMapper.selectOne(queryWrapper);
        if (file1 != null) {
            FileUtil.del(file1.getServerPath());
            fileMapper.deleteById(file1);
        }
        fileMapper.insert(file);
    }

    @Override
    public void deleteFile(File file) {
        delete(file);
    }

    public void delete(File file) {
        if (!Objects.equals(file.getFileType(), "fileFolder")) {
            FileUtil.del(file.getServerPath());
        } else if (Objects.equals(file.getFileType(), "fileFolder")) {
            QueryWrapper<File> queryWrapper = new QueryWrapper<>();
            queryWrapper.like("file_path", file.getFileName());
            List<File> fileList = fileMapper.selectList(queryWrapper);
            List<File> files = new ArrayList<>();
            for (File file1 : fileList) {
                if (file1.getFilePath().length() > file.getFilePath().length()) {
                    files.add(file1);
                }
            }
            for (File file1 : files) {
                delete(file1);
            }
        }
        fileMapper.deleteById(file);
    }

//    @Override
//    public List<File> selectFileListByParentId(Long parentId) {
//        QueryWrapper<File> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("parent_id", parentId);
//        return fileMapper.selectList(queryWrapper);
//    }
//
//    @Override
//    public JSONArray selectTree(Long fileId) {
//        return getNodeJson(fileId);
//    }

    @Override
    public Map<String, List<File>> selectFileListByFilePath(FilePathVO filePathVO) {
        QueryWrapper<File> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("file_path", filePathVO.getFilePath());
        queryWrapper.eq("user_id", filePathVO.getUserId());
        List<File> fileList = fileMapper.selectList(queryWrapper);
        List<File> fileFolderList = new ArrayList<>();
        List<File> documentList = new ArrayList<>();
        Map<String, List<File>> map = new HashMap<>(2);
        for (File file : fileList) {
            if ("fileFolder".equals(file.getFileType())) {
                fileFolderList.add(file);
            } else {
                documentList.add(file);
            }
        }
        fileFolderList.sort(Comparator.comparing(File::getCreateTime).reversed());
        documentList.sort(Comparator.comparing(File::getCreateTime).reversed());
        map.put("fileFolder", fileFolderList);
        map.put("document", documentList);
        return map;
    }


//    /**
//     *
//     * @param nodeId
//     * @return JSONArray
//     */
//    private JSONArray getNodeJson(Long nodeId) {
//        // 找到父亲的所有子节点
//        QueryWrapper<File> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("parent_id", nodeId);
//        List<File> childList = fileMapper.selectList(queryWrapper);
//        JSONArray childTree = new JSONArray();
//        for (File file : childList) {
//            JSONObject object = new JSONObject();
//            object.put("value", file.getFileId());
//            object.put("label", file.getFileName());
//            object.put("type", file.getFileType());
//            JSONArray childs = getNodeJson(file.getFileId());
//            if (!childs.isEmpty()) {
//                object.put("children", childs);
//            }
//            childTree.fluentAdd(object);
//        }
//        return childTree;
//    }
}

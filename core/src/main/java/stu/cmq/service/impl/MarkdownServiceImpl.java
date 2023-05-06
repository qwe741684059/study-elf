package stu.cmq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.error.Mark;
import stu.cmq.annotation.properties.FileProperties;
import stu.cmq.domain.Markdown;
import stu.cmq.domain.User;
import stu.cmq.domain.vo.FilePathVO;
import stu.cmq.exception.BadRequestException;
import stu.cmq.mapper.MarkdownMapper;
import stu.cmq.service.MarkdownService;
import stu.cmq.utils.FileUtil;
import stu.cmq.utils.SecurityUtils;
import stu.cmq.utils.StringUtils;

import javax.validation.constraints.NotBlank;
import java.io.File;
import java.util.*;

/**
 * @author kamifeng
 * @date 15:53
 */

@Service
@RequiredArgsConstructor
public class MarkdownServiceImpl implements MarkdownService {

    private final MarkdownMapper markdownMapper;
    private final FileProperties fileProperties;

    @Override
    public void insertMarkdown(Markdown markdown) {
        markdownMapper.insert(markdown);
    }

    @Override
    public Map<String, List<Markdown>> selectByPath(FilePathVO filePathVO) {
        QueryWrapper<Markdown> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("markdown_path", filePathVO.getFilePath());
        queryWrapper.eq("user_id", filePathVO.getUserId());
        List<Markdown> markdownList = markdownMapper.selectList(queryWrapper);
        List<Markdown> fileFolderList = new ArrayList<>();
        List<Markdown> markdowns = new ArrayList<>();
        Map<String, List<Markdown>> map = new HashMap<>(2);
        for (Markdown markdown : markdownList) {
            if ("fileFolder".equals(markdown.getMarkdownType())) {
                fileFolderList.add(markdown);
            } else {
                markdowns.add(markdown);
            }
        }
        fileFolderList.sort(Comparator.comparing(Markdown::getCreateTime).reversed());
        markdowns.sort(Comparator.comparing(Markdown::getCreateTime).reversed());
        map.put("fileFolder", fileFolderList);
        map.put("markdown", markdowns);
        return map;
    }

    @Override
    public void updateMarkdown(Markdown markdown) {
        markdownMapper.updateById(markdown);
    }

    @Override
    public Markdown selectByMarkdownId(String markdownId) {
        return markdownMapper.selectById(markdownId);
    }

    @Override
    public void deleteMarkdown(Markdown markdown) {
        delete(markdown);
    }

    @Override
    public String uploadImage(MultipartFile multipartFile) {
        String baseUrl = "http://localhost:8181/avatar/";
        String image = "jpg png jpeg";
        // 文件大小验证
        // 验证文件上传的格式
        String fileType = FileUtil.getExtensionName(multipartFile.getOriginalFilename());
        if (fileType != null && !image.contains(fileType)){
            throw new BadRequestException("文件格式错误，仅支持" + image + "格式");
        }

        File file = FileUtil.upload(multipartFile, fileProperties.getAvatar());
        return Objects.requireNonNull(file).getName();

    }

    public void delete(Markdown markdown) {
         if (Objects.equals(markdown.getMarkdownType(), "fileFolder")) {
            QueryWrapper<Markdown> queryWrapper = new QueryWrapper<>();
            queryWrapper.like("markdown_path", markdown.getMarkdownPath());
            List<Markdown> markdownList = markdownMapper.selectList(queryWrapper);
            List<Markdown> markdowns = new ArrayList<>();
             for (Markdown markdown1 : markdownList) {
                 if (markdown1.getMarkdownPath().length() > markdown.getMarkdownPath().length()) {
                     markdowns.add(markdown1);
                 }
             }
            for (Markdown markdown1: markdowns) {
                delete(markdown1);
            }
        }
        markdownMapper.deleteById(markdown);
    }
}

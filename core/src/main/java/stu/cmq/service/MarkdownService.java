package stu.cmq.service;

import org.springframework.web.multipart.MultipartFile;
import stu.cmq.domain.Markdown;
import stu.cmq.domain.vo.FilePathVO;

import java.util.List;
import java.util.Map;

/**
 * @author kamifeng
 * @date 15:53
 */

public interface MarkdownService {

    /**
     * 新建markdown
     * @param markdown
     */
    void insertMarkdown(Markdown markdown);

    /**
     * 根据路径寻找
     * @param filePathVO
     * @return
     */
    Map<String, List<Markdown>> selectByPath(FilePathVO filePathVO);

    /**
     * 修改markdown
     * @param markdown
     */
    void updateMarkdown(Markdown markdown);

    /**
     * 根据markdownId寻找对应的markdown
     * @param markdownId
     * @return
     */
    Markdown selectByMarkdownId(String markdownId);

    /**
     * 删除markdown
     * @param markdown
     */
    void deleteMarkdown(Markdown markdown);

    /**
     * 上传图片
     * @param multipartFile
     * @return String
     */
    String uploadImage(MultipartFile multipartFile);
}

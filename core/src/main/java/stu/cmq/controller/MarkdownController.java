package stu.cmq.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import stu.cmq.domain.Markdown;
import stu.cmq.domain.vo.FilePathVO;
import stu.cmq.service.MarkdownService;

import java.util.List;

/**
 * @author kamifeng
 * @date 16:07
 */

@RestController
@RequestMapping("/markdown")
@Api(tags = "Markdown管理")
public class MarkdownController {

    @Autowired
    private MarkdownService markdownService;

    @PostMapping("/insertMarkdown")
    public ResponseEntity<Object> insertMarkdown(@RequestBody Markdown markdown) {
        markdownService.insertMarkdown(markdown);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/selectByPath")
    public ResponseEntity<Object> selectByPath(@RequestBody FilePathVO filePathVO) {
        return new ResponseEntity<>(markdownService.selectByPath(filePathVO), HttpStatus.OK);
    }

    @PostMapping("/updateMarkdown")
    public ResponseEntity<Object> updateMarkdown(@RequestBody Markdown markdown) {
        markdownService.updateMarkdown(markdown);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/selectByMarkdownId")
    public ResponseEntity<Object> selectByMarkdownId(@RequestBody String markdownId) {
        return new ResponseEntity<>(markdownService.selectByMarkdownId(markdownId), HttpStatus.OK);
    }

    @PostMapping("/deleteMarkdown")
    public ResponseEntity<Object> deleteMarkdown(@RequestBody Markdown markdown) {
        markdownService.deleteMarkdown(markdown);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/uploadImage")
    public ResponseEntity<Object> uploadImage(@RequestParam MultipartFile file) {
        return new ResponseEntity<>(markdownService.uploadImage(file), HttpStatus.OK);
    }
}

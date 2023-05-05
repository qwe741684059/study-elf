package stu.cmq.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import stu.cmq.domain.File;
import stu.cmq.domain.vo.FilePathVO;
import stu.cmq.service.FileService;

/**
 * @author kamifeng
 * @date 19:02
 */


@RestController
@RequestMapping("/file")
@Api(tags = "文件管理")
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping("/selectAll")
    public ResponseEntity<Object> selectAll() {
        return new ResponseEntity<>(fileService.selectAll(), HttpStatus.OK);
    }

    @PostMapping("/insertFile")
    public ResponseEntity<Object> insertFile(@RequestBody File file) {
        fileService.insertFile(file);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/updateFile")
    public ResponseEntity<Object> updateFile(@RequestBody File file) {
        fileService.updateFile(file);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/uploadFile")
    public ResponseEntity<Object> uploadFile(@RequestParam MultipartFile file, @RequestParam String filePath, @RequestParam String userId) {
        fileService.uploadFile(file, filePath, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/deleteFile")
    public ResponseEntity<Object> deleteFile(@RequestBody File file) {
        fileService.deleteFile(file);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//
//    @GetMapping("/selectFileListByParentId")
//    public ResponseEntity<Object> selectFileListByParentId(@RequestParam Long parentId) {
//        return new ResponseEntity<>(fileService.selectFileListByParentId(parentId),HttpStatus.OK);
//    }
//
//    @GetMapping("/selectTree")
//    public ResponseEntity<Object> selectTree() {
//        return new ResponseEntity<>(fileService.selectTree(0L), HttpStatus.OK);
//    }

    @PostMapping("/selectFileListByFilePath")
    public ResponseEntity<Object> selectFileListByFilePath(@RequestBody FilePathVO filePathVO) {
        return new ResponseEntity<>(fileService.selectFileListByFilePath(filePathVO),HttpStatus.OK);
    }

    @RequestMapping ("/callBack")
    public ResponseEntity<Object> callBack() {
        System.out.println("进来了");
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

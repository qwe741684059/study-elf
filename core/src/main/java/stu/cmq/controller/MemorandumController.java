package stu.cmq.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stu.cmq.domain.Memorandum;
import stu.cmq.service.MemorandumService;

/**
 * @author kamifeng
 * @date 2023/4/24
 */

@RestController
@RequestMapping("/memorandum")
@Api(tags = "备忘录管理")
public class MemorandumController {

    @Autowired
    private MemorandumService memorandumService;

    @PostMapping("/insertMemorandum")
    public ResponseEntity<Object> insertMemorandum(@RequestBody Memorandum memorandum) {
        memorandumService.insertMemorandum(memorandum);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/selectMemorandumList")
    public ResponseEntity<Object> selectMemorandumList(@RequestBody String userId) {
        return new ResponseEntity<>(memorandumService.selectMemorandumList(userId), HttpStatus.OK);
    }

    @PostMapping("/deleteMemorandum")
    public ResponseEntity<Object> deleteMemorandum(@RequestBody Memorandum memorandum) {
        memorandumService.deleteMemorandum(memorandum);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/updateMemorandum")
    public ResponseEntity<Object> updateMemorandum(@RequestBody Memorandum memorandum) {
        memorandumService.updateMemorandum(memorandum);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/getTimeList")
    public ResponseEntity<Object> getTimeList(@RequestBody String userId) {
        return new ResponseEntity<>(memorandumService.getTimeList(userId), HttpStatus.OK);
    }
}

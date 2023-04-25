package stu.cmq.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stu.cmq.domain.Course;
import stu.cmq.service.CourseService;

import java.util.List;

/**
 * @author kamifeng
 * @date 2023/4/24
 */

@RestController
@RequestMapping("/course")
@Api(tags = "课程管理")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/insertCourse")
    public ResponseEntity<Object> insertCourse(@RequestBody Course course) {
        courseService.insertCourse(course);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/selectCourseList")
    public ResponseEntity<Object> selectCourseList(@RequestBody String userId) {
        return new ResponseEntity<>(courseService.selectCourseList(userId), HttpStatus.OK);

    }

    @PostMapping("/deleteCourse")
    public ResponseEntity<Object> deleteCourse(@RequestBody Course course) {
        courseService.deleteCourse(course);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping("/updateCourse")
    public ResponseEntity<Object> updateCourse(@RequestBody List<Course> courseList) {
        courseService.updateCourse(courseList);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}

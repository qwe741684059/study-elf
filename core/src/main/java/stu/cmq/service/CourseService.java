package stu.cmq.service;

import stu.cmq.domain.Course;

import java.util.List;

/**
 * @author kamifeng
 * @date 8:45
 */

public interface CourseService {

    /**
     * 新增课程
     * @param course
     */
    void insertCourse(Course course);

    /**
     * 查找课程列表
     * @param userId
     * @return
     */
    List<Course> selectCourseList(String userId);

    /**
     * 删除
     * @param course
     */
    void deleteCourse(Course course);

    /**
     * 修改课程
     * @param courseList
     */
    void updateCourse(List<Course> courseList);
}

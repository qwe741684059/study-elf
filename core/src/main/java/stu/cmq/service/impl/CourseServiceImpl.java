package stu.cmq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import stu.cmq.domain.Course;
import stu.cmq.mapper.CourseMapper;
import stu.cmq.service.CourseService;

import java.util.List;

/**
 * @author kamifeng
 * @date 2023/4/24
 */

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseMapper courseMapper;

    @Override
    public void insertCourse(Course course) {
        // 判断时间内是否有课程，有则删除
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",course.getUserId());
        queryWrapper.eq("course_day",course.getCourseDay());
        queryWrapper.eq("course_start",course.getCourseStart());
        Course course1 = null;
        course1 = courseMapper.selectOne(queryWrapper);
        if (course1 != null) {
            courseMapper.deleteById(course1);
        }

        courseMapper.insert(course);
    }

    @Override
    public List<Course> selectCourseList(String userId) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return courseMapper.selectList(queryWrapper);
    }

    @Override
    public void deleteCourse(Course course) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",course.getUserId());
        queryWrapper.eq("course_name",course.getCourseName());
        queryWrapper.eq("course_day",course.getCourseDay());
        queryWrapper.eq("course_start",course.getCourseStart());
        queryWrapper.eq("course_length",course.getCourseLength());
        courseMapper.delete(queryWrapper);
    }

    @Override
    public void updateCourse(List<Course> courseList) {
        Course rawCourse = courseList.get(0);
        Course updateCourse = courseList.get(1);
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",rawCourse.getUserId());
        queryWrapper.eq("course_name",rawCourse.getCourseName());
        queryWrapper.eq("course_day",rawCourse.getCourseDay());
        queryWrapper.eq("course_start",rawCourse.getCourseStart());
        queryWrapper.eq("course_length",rawCourse.getCourseLength());
        Course course = courseMapper.selectOne(queryWrapper);
        updateCourse.setCourseId(course.getCourseId());
        courseMapper.updateById(updateCourse);
    }
}

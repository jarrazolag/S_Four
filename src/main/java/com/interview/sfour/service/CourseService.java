package com.interview.sfour.service;

import com.interview.sfour.model.Course;
import com.interview.sfour.model.Student;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    List<Course> read();

    Course create(Course classObj);

    Course update(Course classObj, Integer code);

    void delete(Integer code);

    List<Student> readRelatedStudentsById(Integer code);

    Course readById(Integer code);

    List<Course> readByFields(Optional<Integer> code, Optional<String> title, Optional<String> description);
}

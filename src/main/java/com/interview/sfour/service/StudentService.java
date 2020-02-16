package com.interview.sfour.service;

import com.interview.sfour.model.Course;
import com.interview.sfour.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    List<Student> read();

    Student create(Student student);

    Student update(Student student, Integer id);

    void delete(Integer id);

    List<Course> readRelatedClassesById(Integer id);

    void enrollStudent(Integer code, Integer id);

    Student readById(Integer id);

    List<Student> readByFields(Optional<Integer> id, Optional<String> firstName, Optional<String> lastName);
}

